package com.codestack.deepsense.presentation.signup

import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Patterns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codestack.deepsense.R
import com.codestack.deepsense.components.*
import com.codestack.deepsense.core.Utils
import com.codestack.deepsense.navigation.Screens
import com.codestack.deepsense.ui.theme.DeepSenseTheme
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider.getCredential


@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var signUpClicked by rememberSaveable { mutableStateOf(false) }
    var facebookSignUpClicked by rememberSaveable { mutableStateOf(false) }
    var emailInvalid by rememberSaveable { mutableStateOf(false) }
    var passwordEmpty by rememberSaveable { mutableStateOf(false) }
    var nameEmpty by rememberSaveable { mutableStateOf(false) }
    var passwordValidLength by rememberSaveable { mutableStateOf(true) }


    Surface {
        // top progress bar
        Row {
            if (signUpClicked) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        Column(
            Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            // top navigation bar
            AuthTopNavButtons(navController)

            Column(
                Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Sign Up",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 20.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                // Platform signup buttons
                Column {
                    Row {
                        SignUpButton(
                            text = "Sign up with Google",
                            textClicked = "Signing up with Google",
                            isSigningUp = signUpClicked,
                            onClick = {
                                signUpClicked = !signUpClicked
                                viewModel.oneTapSignUp()
                            }
                        )
                    }
                    Row {
                        SignUpButton(
                            text = "Sign up with Facebook",
                            textClicked = "Signing up with Facebook",
                            icon = R.drawable.ic_facebook_logo_circle,
                            isSigningUp = facebookSignUpClicked,
                            onClick = { facebookSignUpClicked = !facebookSignUpClicked }
                        )
                    }
                }

                // Divider
                MyDivider()

                // Email signup
                Column {
                    Row {
                        OutlinedTextField(
                            value = viewModel.name,
                            onValueChange = { newName ->
                                viewModel.onNameChange(newName)
                                nameEmpty = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = null
                                )
                            },
                            label = { Text(text = "Name") },
                            singleLine = true,
                            shape = MaterialTheme.shapes.medium,
                            isError = nameEmpty,
                            enabled = !signUpClicked
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row {
                        EmailTextField(
                            email = viewModel.email,
                            onEmailChanged = { newEmail ->
                                viewModel.onEmailChange(newEmail)
                                emailInvalid = false
                            },
                            isInvalid = emailInvalid,
                            isSigningUp = signUpClicked
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row {
                        PasswordTextField(
                            password = viewModel.password,
                            onPasswordChange = { newPassword ->
                                viewModel.onPasswordChange(newPassword)
                                passwordEmpty = false
                            },
                            isEmpty = passwordEmpty,
                            isSigningUp = signUpClicked
                        )
                    }
                }

                // Sign up button
                Row {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (viewModel.name.isEmpty()) {
                                nameEmpty = true
                            } else if (!isEmailValid(viewModel.email)) {
                                emailInvalid = true
                            } else if (viewModel.password.isEmpty()) {
                                passwordEmpty = true
                            } else if (viewModel.password.length < 8) {
                                passwordValidLength = false
                            } else {
                                passwordValidLength = true
                                signUpClicked = !signUpClicked
                                viewModel.signUpWithEmailAndPassword()
                            }
                        },
                        enabled = !signUpClicked
                    ) {
                        Text(
                            modifier = Modifier.padding(0.dp, 5.dp),
                            text = if (signUpClicked) "Signing Up" else "Sign Up",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        )
                    }
                }
            }
        }
    }

    if (facebookSignUpClicked) {
        FeatureIncomingDialog { facebookSignUpClicked = false }
    }

    if (!passwordValidLength) {
        PasswordLengthErrorDialog { passwordValidLength = true }
    }

    // Google one tap sign in
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                try {
                    val credentials =
                        viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                    val googleIdToken = credentials.googleIdToken
                    val googleCredentials = getCredential(googleIdToken, null)
                    if (signUpClicked) {
                        viewModel.signInWithGoogle(googleCredentials)
                    }
                } catch (it: ApiException) {
                    print(it)
                }
            }
        }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(
        launch = {
            launch(it)
        }
    )

    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                if (isServiceEnabled(context)) {
                    navController.navigate(Screens.Main.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                } else {
                    navController.navigate(Screens.Accessibility.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    )

    // Email sign up
    EmailSignUp(
        navigateToHomeScreen = { signedUp ->
            if (signedUp) {
                if (isServiceEnabled(context)) {
                    navController.navigate(Screens.Main.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                } else {
                    navController.navigate(Screens.Accessibility.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    )
}


fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    DeepSenseTheme {
        SignUpScreen(navController = rememberNavController())
    }
}

private fun isServiceEnabled(context: Context): Boolean {
    return Utils.sharedPrefGetValue(context, "isAccessibilityServiceEnabled") as? Boolean ?: false
}