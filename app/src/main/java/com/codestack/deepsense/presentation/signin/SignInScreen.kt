package com.codestack.deepsense.presentation.signin


import android.app.Activity
import android.util.Patterns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.codestack.deepsense.R
import com.codestack.deepsense.components.*
import com.codestack.deepsense.navigation.Screens
import com.codestack.deepsense.presentation.signup.*
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable

fun SignInScreen(
    navController: NavHostController,
    viewModel: SignInViewModel = hiltViewModel()
) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }

    var signInClicked by rememberSaveable { mutableStateOf(false) }
    var facebookSignInClicked by rememberSaveable { mutableStateOf(false) }
    var emailInvalid by rememberSaveable { mutableStateOf(false) }
    var passwordEmpty by rememberSaveable { mutableStateOf(false) }


    Surface {
        // top progress bar
        Row {
            if (signInClicked) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

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
                        text = "Sign in",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 20.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                // Platform signin buttons
                Column {
                    Row {
                        SignUpButton(
                            text = "Sign in with Google",
                            textClicked = "Signing in with Google",
                            isSigningUp = signInClicked,
                            onClick = {
                                signInClicked = !signInClicked
                                viewModel.oneTapSignIn()
                            }
                        )
                    }
                    Row {
                        SignUpButton(
                            text = "Sign in with Facebook",
                            textClicked = "Signing in with Facebook",
                            icon = R.drawable.ic_facebook_logo_circle,
                            isSigningUp = facebookSignInClicked,
                            onClick = { facebookSignInClicked = !facebookSignInClicked }
                        )
                    }

                    Spacer(modifier = Modifier.padding(top=50.dp))
                    MyDivider()
                    Spacer(modifier = Modifier.padding(bottom=50.dp))
                    // Email signup
                    Column {

                        Row {
                            EmailTextField(
                                email = viewModel.email,
                                onEmailChanged = { newEmail ->
                                    viewModel.onEmailChange(newEmail)
                                    emailInvalid = false
                                },
                                isInvalid = emailInvalid,
                                isSigningUp = signInClicked
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
                                isSigningUp = signInClicked
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    // Sign up button
                    Row {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if (!isEmailValid(viewModel.email)) {
                                    emailInvalid = true
                                } else if (viewModel.password.isEmpty()) {
                                    passwordEmpty = true
                                } else {
                                    signInClicked = !signInClicked
                                    viewModel.signInWithEmailAndPassword()
                                }
                            },
                            enabled = !signInClicked
                        ) {
                            Text(
                                modifier = Modifier.padding(0.dp, 5.dp),
                                text = if (signInClicked) "Signing in" else "Sign in",
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            )
                        }
                    }

                    // Bottom Section new user?
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,

                        modifier = Modifier
//                .clickable { MutableInteractionSource() }
                            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                            .align(alignment = Alignment.CenterHorizontally),

                        )

                    {
                        Text(
                            text = "New user?",
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center,
                            fontSize = 13.sp,
                            color = androidx.compose.ui.graphics.Color.Gray,

                            )

                        TextButton(
                            onClick = { /*TODO*/ },
                        ) {
                            Text(
                                "Sign up",
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = androidx.compose.ui.graphics.Color.Gray,
                                modifier = Modifier
                                    .clickable { navController.navigate(Screens.SignUp.route) }
                                    .padding(bottom = 2.dp),
                            )
                        }
                    }
                }

            }
        }
    }


    if (facebookSignInClicked) {
        FeatureIncomingDialog { facebookSignInClicked = false }
    }

    // Google one tap sign in
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    val credentials =
                        viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                    val googleIdToken = credentials.googleIdToken
                    val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                    if (signInClicked) {
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
                navController.navigate(Screens.Main.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
    )

    // Email sign in
    EmailSignIn(
        navigateToHomeScreen = { signedUp ->
            if (signedUp) {
                navController.navigate(Screens.Main.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
    )
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}












//@Composable
//fun RowScope.CustomDivider(modifier: Modifier) {
//    Surface(
//        modifier = Modifier
//            .weight(1f)
//        //.padding(start = 50.dp)
//    ) {
//        Divider(color = Color.Gray, modifier = modifier)
//
//    }
//}

//fun logClicked(email: String, password: String, context: Context) {
//
//    if (email == "" || password == "") {
//        Toast.makeText(context, "Logging fail", Toast.LENGTH_SHORT).show()
//        println("Empty fields")
//    } else {
//        Toast.makeText(context, "Logging Successfully", Toast.LENGTH_SHORT).show()
//        println("Logging in")
//    }
//}