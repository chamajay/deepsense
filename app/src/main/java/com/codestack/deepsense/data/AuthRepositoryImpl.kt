package com.codestack.deepsense.data

import androidx.compose.ui.platform.LocalContext
import com.codestack.deepsense.core.Constants.CREATED_AT
import com.codestack.deepsense.core.Constants.DISPLAY_NAME
import com.codestack.deepsense.core.Constants.EMAIL
import com.codestack.deepsense.core.Constants.PHOTO_URL
import com.codestack.deepsense.core.Constants.SIGN_IN_REQUEST
import com.codestack.deepsense.core.Constants.SIGN_UP_REQUEST
import com.codestack.deepsense.core.Constants.USERS
import com.codestack.deepsense.core.Utils
import com.codestack.deepsense.domain.model.Response.Success
import com.codestack.deepsense.domain.model.Response.Failure
import com.codestack.deepsense.domain.repository.*
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


// adapted from -
// https://github.com/alexmamo/FirebaseSignInWithGoogle,
// https://github.com/alexmamo/FirebaseSignInWithEmailAndPassword
@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private val db: FirebaseFirestore
) : AuthRepository {
    override val isUserAuthenticatedInFirebase = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Success(signUpResult)
            } catch (e: Exception) {
                Failure(e)
            }
        }
    }

    override suspend fun oneTapSignUpWithGoogle(): OneTapSignInResponse {
        return try {
            val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
            Success(signUpResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signInRequest).await()
                Success(signUpResult)
            } catch (e: Exception) {
                Failure(e)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): SignInWithGoogleResponse {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToFirestore()
            }
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    private suspend fun addUserToFirestore() {
        auth.currentUser?.apply {
            val user = toUser()
            db.collection(USERS).document(uid).set(user).await()
        }
    }

    private suspend fun addEmailUserToFirestore(name: String) {
        if (name.isNotEmpty()) {
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()
            auth.currentUser?.updateProfile(profileUpdates)?.await()
        }
        auth.currentUser?.apply {
            val user = toUser()
            db.collection(USERS).document(uid).set(user).await()
        }
    }

    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String, password: String, name: String
    ): SignUpResponse {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addEmailUserToFirestore(name)
            }
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ): SignInResponse {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}

fun FirebaseUser.toUser() = mapOf(
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString(),
    CREATED_AT to serverTimestamp()
)