package com.codestack.deepsense.screens.aboutus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.codestack.deepsense.R

@Composable
fun AboutUsScreen() {
    Surface{
        Column(modifier=Modifier.fillMaxHeight()) {

            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "About us",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "We are a team of passionate developers who are Software engineering undergraduate students of informatics institute of technology. We are dedicated to delivering high-quality apps to make people's lives easier.",
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Spacer(modifier = Modifier.height(50.dp))
                Text("Team Members:")

            }
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
                    text = " R. M. C. S. Jayasena ",

                    )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.mem),
                    "member2",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        )
                )
                Text(
                    text = " R.A.Kumudu Wijewardhana ",
                    textAlign = TextAlign.Right
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.mem),
                    "member3",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        )
                )
                Text(
                    text = " U.G.T.Thakshali ",
                    textAlign = TextAlign.Right
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.mem),
                    "member4",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        )
                )
                Text(
                    text = " M.R.M. Perera ",
                    textAlign = TextAlign.Right
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.mem),
                    "member5",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        )
                )
                Text(
                    text = " S.C Dissanayake ",
                    textAlign = TextAlign.Right
                )
            }

        }

    }


}
