package com.codestack.deepsense.domain.repository

import com.codestack.deepsense.domain.model.Response
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential

typealias OneTapSignInResponse = Response<BeginSignInResult>
typealias SignInWithGoogleResponse = Response<Boolean>
typealias SignUpResponse = Response<Boolean>
typealias SignInResponse = Response<Boolean>

// adapted from -
// https://github.com/alexmamo/FirebaseSignInWithGoogle,
// https://github.com/alexmamo/FirebaseSignInWithEmailAndPassword
interface AuthRepository {
    val isUserAuthenticatedInFirebase: Boolean

    suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse

    suspend fun oneTapSignUpWithGoogle(): OneTapSignInResponse

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse

    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String, name: String): SignUpResponse

    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInResponse
}