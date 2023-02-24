package com.codestack.deepsense.presentation.signup

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codestack.deepsense.R
import com.codestack.deepsense.components.*
import com.codestack.deepsense.ui.theme.DeepSenseTheme


@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    var signUpClicked by rememberSaveable { mutableStateOf(false) }
    var facebookSignUpClicked by rememberSaveable { mutableStateOf(false) }
    var emailInvalid by rememberSaveable { mutableStateOf(false) }
    var passwordEmpty by rememberSaveable { mutableStateOf(false) }

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
                            onClick = { signUpClicked = !signUpClicked }
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
                        EmailTextField(
                            email = uiState.email,
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
                            password = uiState.password,
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
                            if (!isEmailValid(uiState.email)) {
                                emailInvalid = true
                            } else if (uiState.password.isEmpty()) {
                                passwordEmpty = true
                            } else {
                                signUpClicked = !signUpClicked
                                viewModel.onSignUpClick(navController)
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