package com.codestack.deepsense.screens.contactus

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .fillMaxHeight()

    ){
        Text(text = "Contact Us", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(30.dp))

        Card(Modifier.size(width = 350.dp, height = 140.dp)) {
            Spacer(modifier = Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    painter = painterResource(R.drawable.mail_48px),
                    "mail",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        )

                )
                Text(text = "  Thimasha.20210464@iit.ac.lk")

            }
            Spacer(modifier = Modifier.height(20.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    painter = painterResource(R.drawable.call_48px),
                    "call",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        )

                )
                Text(text = "  011-XXXXXXX")

            }
        }
        Spacer(modifier = Modifier.height(40.dp))

        Card(Modifier.size(width = 350.dp, height = 400.dp)) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "   Please fill out the form below to contact us.",
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "Name",
                onValueChange = {},
                label = { Text(text = "Name") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "Email",
                onValueChange = {},
                label = { Text(text = "Email") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "message",
                onValueChange = {},
                label = { Text(text = "Message") },
                maxLines = 10
            )
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { }) {
                Text("Send Email")
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