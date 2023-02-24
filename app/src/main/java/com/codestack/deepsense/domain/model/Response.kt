package com.codestack.deepsense.domain.model

// adapted from - https://github.com/alexmamo/FirebaseSignInWithGoogle
sealed class Response<out T> {
    object Loading: Response<Nothing>()

    data class Success<out T>(
        val data: T?
    ): Response<T>()

    data class Failure(
        val e: Exception
    ): Response<Nothing>()
}