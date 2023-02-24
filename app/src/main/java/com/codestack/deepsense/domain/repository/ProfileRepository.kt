package com.codestack.deepsense.domain.repository

import com.codestack.deepsense.domain.model.Response

typealias SignOutResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>

// adapted from - https://github.com/alexmamo/FirebaseSignInWithGoogle
interface ProfileRepository {
    val displayName: String
    val photoUrl: String

    suspend fun signOut(): SignOutResponse

    suspend fun revokeAccess(): RevokeAccessResponse
}