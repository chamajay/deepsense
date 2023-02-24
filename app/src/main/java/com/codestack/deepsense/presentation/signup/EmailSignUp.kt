package com.codestack.deepsense.presentation.signup

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.codestack.deepsense.core.Constants
import com.codestack.deepsense.domain.model.Response.Loading
import com.codestack.deepsense.domain.model.Response.Success
import com.codestack.deepsense.domain.model.Response.Failure

@Composable
fun EmailSignUp(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateToHomeScreen: (signedIn: Boolean) -> Unit
) {
    when (val signUpResponse = viewModel.signUpResponse) {
        is Loading -> Log.d(Constants.TAG, "Loading")
        is Success -> signUpResponse.data?.let { signedUp ->
            LaunchedEffect(signedUp) {
                navigateToHomeScreen(signedUp)
            }
        }
        is Failure -> signUpResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}