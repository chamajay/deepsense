package com.codestack.deepsense.presentation.aboutus

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codestack.deepsense.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen() {

    Surface{
        Column(modifier=Modifier.fillMaxHeight()) {


            Row {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_48px),
                    "back arrow",
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(65.dp))
                Text(
                    "About us",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "We are a team of passionate developers who are Software engineering undergraduate students of informatics institute of technology. We are dedicated to delivering high-quality apps to make people's lives easier.",
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )

            }
            Spacer(modifier = Modifier.height(5.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Team Members:",fontWeight = FontWeight.Bold)

                }
                Spacer(modifier = Modifier.height(10.dp))

                Card(Modifier.size(width = 380.dp, height = 90.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            painter = painterResource(R.drawable.account_circle_48px),
                            "member1",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(
                                    RoundedCornerShape(50.dp)
                                )
                        )
                        Text(
                            text = " Chamath Jayasena ",
                        )
                    }}

            Spacer(modifier = Modifier.height(10.dp))

            Card(Modifier.size(width = 380.dp, height = 90.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(R.drawable.account_circle_48px),
                        "member2",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(
                                RoundedCornerShape(50.dp)
                            )
                    )
                    Text(
                        text = " Kumudu Wijewardhana ",
                    )
                } }

            Spacer(modifier = Modifier.height(10.dp))

            Card(Modifier.size(width = 380.dp, height = 90.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(R.drawable.account_circle_48px),
                        "member3",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(
                                RoundedCornerShape(50.dp)
                            )
                    )
                    Text(
                        text = " Thimasha Thakshali ",
                        textAlign = TextAlign.Right
                    )
                }}

            Spacer(modifier = Modifier.height(10.dp))

            Card(Modifier.size(width = 380.dp, height = 90.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(R.drawable.account_circle_48px),
                        "member4",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(
                                RoundedCornerShape(50.dp)
                            )
                    )
                    Text(
                        text = " Ruwindi Perera ",
                        textAlign = TextAlign.Right
                    )
                }}

            Spacer(modifier = Modifier.height(10.dp))

            Card(Modifier.size(width = 380.dp, height =90.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(R.drawable.account_circle_48px),
                        "member5",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(
                                RoundedCornerShape(50.dp)
                            )
                    )
                    Text(
                        text = " Sahan Dissanayake ",
                        textAlign = TextAlign.Right
                    )
                }}
        }
    }


}

@Preview
@Composable
fun AboutUsScreenPreview()
{
    AboutUsScreen()
    
}