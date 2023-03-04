package com.codestack.deepsense.presentation.signin

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.codestack.deepsense.core.Constants.TAG
import com.codestack.deepsense.domain.model.Response.Success
import com.codestack.deepsense.domain.model.Response.Loading
import com.codestack.deepsense.domain.model.Response.Failure

// adapted from - https://github.com/alexmamo/FirebaseSignInWithGoogle
@Composable
fun SignInWithGoogle(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToHomeScreen: (signedIn: Boolean) -> Unit
) {
    when (val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
        is Loading -> Log.d(TAG, "Loading")
        is Success -> signInWithGoogleResponse.data?.let { signedIn ->
            LaunchedEffect(signedIn) {
                navigateToHomeScreen(signedIn)
            }
        }
        is Failure -> LaunchedEffect(Unit) {
            print(signInWithGoogleResponse.e)
        }
    }
}