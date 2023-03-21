package com.codestack.deepsense.presentation.contactus

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codestack.deepsense.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsScreen()
{
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = "Contact Us",
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp
                    )
                },
                navigationIcon = { Icon(Icons.Filled.ArrowBack, contentDescription = "Go Back") }
                )
        },
        content = {

            Content()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content() {
    // fetch local context
    val lContext = LocalContext.current
    val mail = "codestack@gmail.com"
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .fillMaxHeight()
        )
        {
            Spacer(modifier = Modifier.height(100.dp))
            LazyColumn() {

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Card(Modifier.size(width = 350.dp, height = 100.dp)) {
                            Spacer(modifier = Modifier.height(30.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Spacer(modifier = Modifier.width(40.dp))
                                Icon(
                                    painter = painterResource(R.drawable.mail_48px),
                                    "mail",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(
                                            RoundedCornerShape(50.dp)
                                        )
                                )

                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = "  $mail",
                                    modifier = Modifier.clickable(onClick = {
                                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                                            data = Uri.parse("mailto:$mail")
                                        }
                                        context.startActivity(intent)
                                    })
                                )

                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    )
                    {
                        Card(Modifier.size(width = 350.dp, height = 410.dp)) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "   Please fill out the form below to contact us.",
                            )
                            Spacer(modifier = Modifier.height(30.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            )

                            {
                                OutlinedTextField(
                                    value = userName,
                                    onValueChange = { userName = it },
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                                    label = { Text("Name") }
                                )
                            }

                            Spacer(modifier = Modifier.height(25.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {

                                OutlinedTextField(
                                    value = email,
                                    onValueChange = {
                                        email = it
                                        emailError = !isValidEmail(it)
                                    },
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                                    label = { Text("Email") },
                                    isError = emailError,

                                )
                            }

                            Spacer(modifier = Modifier.height(25.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {

                                OutlinedTextField(
                                    value = message,
                                    onValueChange = { message = it },
                                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send, autoCorrect = true),
                                    label = { Text("Message") },
                                    maxLines = 10
                                )
                            }

                            Spacer(modifier = Modifier.height(25.dp))

                            Row(modifier = Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.width(230.dp))
                                Button(onClick = {
                                    if(userName.isEmpty()){
                                        Toast.makeText(
                                            lContext, "Please enter your name",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    if (email.isEmpty())
                                    { Toast.makeText(
                                                lContext,
                                                "Please enter your Email",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                    }
                                    if(message.isEmpty()){
                                        Toast.makeText(lContext,
                                            "Please enter your message",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    if(userName.isNotEmpty() and email.isNotEmpty() and message.isNotEmpty())
                                    {
                                        if(!isValidEmail(email))
                                        {
                                            Toast.makeText(lContext,
                                                "Please enter a valid Email",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        else {
                                            Toast.makeText(
                                                lContext,
                                                "Message send Successfully ",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }) {
                                    Text(" Send ")
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    return emailRegex.matches(email)
}

@Preview
@Composable
fun ContactUsScreenPreview()
{
    ContactUsScreen()

}