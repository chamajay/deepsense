package com.codestack.deepsense.screens.home

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.codestack.deepsense.model.service.AccountService
import com.codestack.deepsense.navigation.Screens
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountService: AccountService,
) : ViewModel() {
    val userId get() = accountService.currentUserId
    val user get() = accountService.user
}