package com.codestack.deepsense.presentation.signin

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.codestack.deepsense.core.Constants
import com.codestack.deepsense.domain.model.Response.Loading
import com.codestack.deepsense.domain.model.Response.Success
import com.codestack.deepsense.domain.model.Response.Failure

// adapted from - https://github.com/alexmamo/FirebaseSignInWithEmailAndPassword
@Composable
fun EmailSignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToHomeScreen: (signedIn: Boolean) -> Unit
) {
    when (val signInResponse = viewModel.signInResponse) {
        is Loading -> Log.d(Constants.TAG, "Loading")
        is Success -> signInResponse.data?.let { signedUp ->
            LaunchedEffect(signedUp) {
                navigateToHomeScreen(signedUp)
            }
        }
        is Failure -> signInResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}
