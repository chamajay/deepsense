package com.codestack.deepsense.presentation.contactus

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
fun ContactUsScreen()
{
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .fillMaxHeight()
        )
        {
            Row {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_48px),
                    "back arrow",
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(60.dp))

                Text(
                    text = "Contact Us",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(70.dp))
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Card(Modifier.size(width = 350.dp, height = 140.dp)) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
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
                    Spacer(modifier = Modifier.height(20.dp))

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

                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

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
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center)
                    {

                        OutlinedTextField(
                            value = "Name",
                            onValueChange = {},
                            label = { Text(text = "Name") }
                        )}
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {

                        OutlinedTextField(
                            value = "Email",
                            onValueChange = {},
                            label = { Text(text = "Email") }
                    )}
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {

                        OutlinedTextField(
                            value = "message",
                            onValueChange = {},
                            label = { Text(text = "Message") },
                            maxLines = 10
                    )}
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.width(200.dp))
                        Button(onClick = { }) {
                            Text("Send Email")
                    }}
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