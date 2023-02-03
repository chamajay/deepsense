package com.codestack.deepsense.screens.signup

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codestack.deepsense.model.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
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

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignUpClick() {
        runBlocking {
            accountService.createEmailAccount(email, password)
//            accountService.authenticate("hehe@gmail.com", "123456")
        }
    }
}