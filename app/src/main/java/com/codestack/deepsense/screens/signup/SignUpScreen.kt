package com.codestack.deepsense.screens.signup

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codestack.deepsense.R
import com.codestack.deepsense.ui.theme.DeepSenseTheme

@Composable
fun SignUpButton(
    modifier: Modifier = Modifier,
    text: String = "Sign Up with Google",
    textClicked: String = "Signing Up with Google",
    icon: Int = R.drawable.ic_google_logo,
    isSigningUp: Boolean = false,
    onClick: () -> Unit,
) {
    var clicked by remember { mutableStateOf(false) }
    OutlinedButton(
        onClick = {
            onClick()
            clicked = !clicked
        },
        enabled = !isSigningUp,
        modifier = modifier
            .fillMaxWidth()
            .padding(40.dp, 10.dp)
    ) {
        Icon(
            modifier = modifier.size(24.dp),
            painter = painterResource(id = icon),
            contentDescription = "platform icon",
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = if (clicked) textClicked else text,
            modifier = Modifier.padding(0.dp, 5.dp),
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}


@Composable
fun TopNavi(navController: NavHostController) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        // Back button
        OutlinedButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Skip button
        OutlinedButton(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Skip")
        }
    }
}


@Composable
fun MyDivider(text: String = "OR") {
    Row(
        modifier = Modifier.padding(30.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color.Gray
        )
        Text(
            modifier = Modifier.padding(5.dp, 0.dp),
            text = text,
            color = Color.Gray,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
        Divider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color.Gray
        )
    }
}


@Composable
fun EmailTextField(
    email: String,
    onEmailChanged: (String) -> Unit,
    isInvalid: Boolean = false,
    isSigningUp: Boolean = false
) {
    OutlinedTextField(
        value = email,
        onValueChange = { newEmail -> onEmailChanged(newEmail) },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = { Icon(imageVector = Icons.Outlined.Email, contentDescription = null) },
        label = { Text(text = "Email") },
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        isError = isInvalid,
        enabled = !isSigningUp
    )
}


@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    isEmpty: Boolean = false,
    isSigningUp: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = { newPassword -> onPasswordChange(newPassword) },
        leadingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = null) },
        label = { Text(text = "Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        shape = MaterialTheme.shapes.medium,
        trailingIcon = {
            val painter = if (passwordVisible) painterResource(id = R.drawable.ic_visibility)
            else painterResource(id = R.drawable.ic_visibility_off)
            IconButton(
                onClick = { passwordVisible = !passwordVisible }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painter,
                    contentDescription = "visibility"
                )
            }
        },
        isError = isEmpty,
        enabled = !isSigningUp
    )
}


@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    var signUpClicked by rememberSaveable { mutableStateOf(false) }
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
            TopNavi(navController)

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
                            isSigningUp = signUpClicked,
                            onClick = { signUpClicked = !signUpClicked }
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
//                            viewModel.onSignUpClick()
                            }
                        }
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
}


fun isEmailValid(email: String): Boolean {
//    return email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"))
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    DeepSenseTheme {
        SignUpScreen(navController = rememberNavController())
    }
}