package com.codestack.deepsense.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codestack.deepsense.model.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountService: AccountService,
) : ViewModel() {
    val userId get() = accountService.currentUserId
    val user get() = accountService.user
    var uiState = mutableStateOf(HomeUiState())
        private set
}