package com.codestack.deepsense.data

import com.codestack.deepsense.core.Constants.USERS
import com.codestack.deepsense.domain.model.Response.Success
import com.codestack.deepsense.domain.model.Response.Failure
import com.codestack.deepsense.domain.repository.ProfileRepository
import com.codestack.deepsense.domain.repository.RevokeAccessResponse
import com.codestack.deepsense.domain.repository.SignOutResponse
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


// adapted from - https://github.com/alexmamo/FirebaseSignInWithGoogle
@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    private var signInClient: GoogleSignInClient,
    private val db: FirebaseFirestore
) : ProfileRepository {
//    override val displayName = auth.currentUser?.displayName.toString()
    override val isUserAuthenticated = auth.currentUser != null
    override val displayName = (auth.currentUser?.displayName ?: "Guest")
    override val photoUrl = auth.currentUser?.photoUrl.toString()

    override suspend fun signOut(): SignOutResponse {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun revokeAccess(): RevokeAccessResponse {
        return try {
            auth.currentUser?.apply {
                db.collection(USERS).document(uid).delete().await()
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
                delete().await()
            }
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}