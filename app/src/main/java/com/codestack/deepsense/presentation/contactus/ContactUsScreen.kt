package com.codestack.deepsense.presentation.contactus

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(modifier = Modifier.width(30.dp))
                                Icon(
                                    painter = painterResource(R.drawable.mail_48px),
                                    "mail",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(
                                            RoundedCornerShape(50.dp)
                                        )

                                )
                                Text(text = "  XXXXX.XXXXXXXX@iit.ac.lk")

                            }
                            //Spacer(modifier = Modifier.height(20.dp))
/*
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(modifier = Modifier.width(30.dp))
                                Icon(
                                    painter = painterResource(R.drawable.call_48px),
                                    "call",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(
                                            RoundedCornerShape(50.dp)
                                        )

                                )
                                Text(text = "  0XX-XXXXXXX")

                            }*/
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
                                    value = "Name",
                                    onValueChange = {},
                                    label = { Text(text = "Name") }
                                )
                            }
                            Spacer(modifier = Modifier.height(25.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {

                                OutlinedTextField(
                                    value = "Email",
                                    onValueChange = {},
                                    label = { Text(text = "Email") }
                                )
                            }
                            Spacer(modifier = Modifier.height(25.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {

                                OutlinedTextField(
                                    value = "message",
                                    onValueChange = {},
                                    label = { Text(text = "Message") },
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