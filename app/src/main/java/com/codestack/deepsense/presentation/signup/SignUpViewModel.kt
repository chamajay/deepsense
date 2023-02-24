package com.codestack.deepsense.presentation.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.codestack.deepsense.model.service.AccountService
import com.codestack.deepsense.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
) : ViewModel() {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    private val signUpClicked
        get() = uiState.value.signUpClicked

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignUpClick(navController: NavHostController) {
        runBlocking {
            accountService.createEmailAccount(email, password)
            navController.navigate(Screens.Home.route)
//            accountService.authenticate("hehe@gmail.com", "123456")
        }
    }
}