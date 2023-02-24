package com.codestack.deepsense.presentation.signup

data class SignUpUiState (
    val email: String = "",
    val password: String = "",
    val signUpClicked: Boolean = false
)