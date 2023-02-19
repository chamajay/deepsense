package com.codestack.deepsense.screens.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codestack.deepsense.R
import com.codestack.deepsense.navigation.Screens

@Composable

fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    // var user by remember { mutableStateOf(Firebase.auth.currentUser) }
//    val launcher = rememberFirebaseAuthLauncher(
//        onAuthComplete = { result ->
//            user = result.user
//        },
//        onAuthError = {
//            user = null
//        }
//    )

    // val token = stringResource(R.string.default_web_client_id)
    // val context = LocalContext.current
    //Spacer(modifier = Modifier.width(30.dp).padding(bottom = 100.dp))
    // Next btn
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End,
    ) {
        OutlinedButton(onClick = {
            navController.navigate(Screens.Home.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
        ) {
            Text(text = "Skip", letterSpacing = 1.sp)
            Spacer(modifier = Modifier.width(5.dp))
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
    }

    // previous btn
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Text(
            text = "Login",
            textAlign = TextAlign.Left,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)

        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Enter your email") },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email icon")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            shape = MaterialTheme.shapes.small
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Enter your password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password icon")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp),
            //.background(MaterialTheme.colorScheme.primaryContainer),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            //         KeyboardActions = KeyboardActions(onDone = {
//                //TODO: keyboard action btn event here

//           }),
            singleLine = true,
            //shape = RoundedCornerShape(topEnd = 16.dp)
        )
        // Login Btn
        Button(
            onClick = {

//                logClicked(email,password,context)
//                auth.signInWithEmailAndPassword(email,password)
//                    .addOnCompleteListener{
//                        if(it.isSuccessful){
//                            Toast.makeText(context, "Valid account", Toast.LENGTH_SHORT).show()
//                            Log.d(ContentValues.TAG,"The user successfully logging to the application")
//                        }else{
//                            Toast.makeText(context, "Invalid Account", Toast.LENGTH_SHORT).show()
//                            Log.d(ContentValues.TAG,"Logging fail")
//                        }
//                    }
            },
            //border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(20.dp),

            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp),
        )


        {

            Text(
                text = "Login",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(
            modifier = Modifier
                .width(30.dp)
                .padding(10.dp)
        )
        //Text(text = "OR", color = androidx.compose.ui.graphics.Color.Gray, textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 10.dp))


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomDivider(Modifier.padding(start = 50.dp))

            Text(
                "OR",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            )

            CustomDivider(Modifier.padding(end = 50.dp))
        }
        Spacer(
            modifier = Modifier
                .width(30.dp)
                .padding(10.dp)
        )

//            if(user != null){
//
//            //val name = user!!.displayName.toString()
//            //Toast.makeText(context,"Welcome $name",Toast.LENGTH_SHORT).show()
////            Log.d(TAG,"user:"+user!!.displayName.toString());
//        }
        // Continue with Google Btn
        Button(
            onClick = {
                //val username = googleSignInClient.silentSignIn().result.displayName
                //Toast.makeText(context,username,Toast.LENGTH_SHORT).show()
                // print(username)
                // val gso =
//                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                        .requestIdToken(token)
//                        .requestEmail()
//                        .build()
//                val googleSignInClient = GoogleSignIn.getClient(context, gso)

//                launcher.launch(googleSignInClient.signInIntent)
                //res = googleSignInClient.silentSignIn().result.displayName.toString()


                //googleSignIn(token,context,launcher)
            },

            shape = RoundedCornerShape(10.dp),

//            colors = ButtonDefaults.buttonColors(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.primary),
            //colors = ButtonDefaults.buttonColors(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),

            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp)
        )


        {

            Image(

                painterResource(R.drawable.ic_google_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(25.dp)
                    .alignBy(FirstBaseline)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Continue with Google",
                textAlign = TextAlign.Center,


                )
        }


        //Sign in with Facebook Btn
        Button(
            onClick = { },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),

            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp)
        )


        {

            Image(

                painterResource(R.drawable.ic_facebook_logo_circle),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(25.dp)
                    .alignBy(FirstBaseline)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Sign in with Facebook",
                textAlign = TextAlign.Center,

                )
        }

        Spacer(modifier = Modifier.padding(top = 20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
//                .clickable { MutableInteractionSource() }
                .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        )
        {
            Text(
                text = "More information?",
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                fontSize = 13.sp,
                color = Color.Gray,
                //modifier = Modifier.padding(end = 1.dp)
            )

            TextButton(
                onClick = { /*TODO*/ },
                ) {
                Text(
                    "Register",
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    color = Color.Gray,
                    modifier = Modifier.clickable { navController.navigate(Screens.SignUp.route) },
                )
            }
        }

        //Toast.makeText(context,"Welcome ${auth.currentUser?.displayName}",Toast.LENGTH_SHORT).show()
        //Toast.makeText(context,"Sign in status $res",Toast.LENGTH_SHORT).show()


    }
}


@Composable
fun RowScope.CustomDivider(modifier: Modifier) {
    Surface(
        modifier = Modifier
            .weight(1f)
        //.padding(start = 50.dp)
    ) {
        Divider(color = Color.Gray, modifier = modifier)

    }
}

fun logClicked(email: String, password: String, context: Context) {

    if (email == "" || password == "") {
        Toast.makeText(context, "Logging fail", Toast.LENGTH_SHORT).show()
        println("Empty fields")
    } else {
        Toast.makeText(context, "Logging Successfully", Toast.LENGTH_SHORT).show()
        println("Logging in")
    }
}