package com.codestack.deepsense.screens.signup

data class SignUpUiState (
    val email: String = "",
    val password: String = "",
    val signUpClicked: Boolean = false
)