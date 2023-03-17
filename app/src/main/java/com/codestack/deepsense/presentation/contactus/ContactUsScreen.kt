package com.codestack.deepsense.presentation.contactus

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
    val mail = "codestack@gmail.com"
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
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
                    ) {
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
                                    value = name,
                                    onValueChange = { name = it },
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
                                    onValueChange = { email = it },
                                    label = { Text("Email") }
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
                                    label = { Text("Message") },
                                    maxLines = 10
                                )
                            }
                            Spacer(modifier = Modifier.height(25.dp))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.width(200.dp))
                                Button(onClick = { }) {
                                    Text("Send Email")
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun ContactUsScreenPreview()
{
    ContactUsScreen()

}