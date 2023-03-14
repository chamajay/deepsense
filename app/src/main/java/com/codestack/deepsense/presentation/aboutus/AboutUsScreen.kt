package com.codestack.deepsense.presentation.aboutus

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codestack.deepsense.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen()
{ Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {

                    Text(
                        text = "About Us",
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp
                    )

                },
                navigationIcon = { Icon(Icons.Filled.ArrowBack, contentDescription = "Go Back") },
                actions = { /* create your action items here */ },

            )
        },
        content = {
            Content()
        }
    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberInfo(
    memNo : String,
    memImage: Int,
    name: String

) {
    Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Card(Modifier.size(width = 350.dp, height = 80.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = memImage),
                    memNo,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(
                            RoundedCornerShape(50.dp)
                        )
                )
                Text(
                    text = name,
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun Content() {
    Surface {
        //Spacer(modifier = Modifier.height(110.dp))
        Column(modifier = Modifier.fillMaxHeight())
        {
            Spacer(modifier = Modifier.height(80.dp))
            LazyColumn() {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "We are a team of passionate developers who are Software engineering undergraduate students of informatics institute of technology. We are dedicated to delivering high-quality apps to make people's lives easier.",
                            maxLines = 6,
                            overflow = TextOverflow.Ellipsis
                        )

                    }
                }
                item {

                    Spacer(modifier = Modifier.height(15.dp))
                    Row {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Team Members:", fontWeight = FontWeight.Bold)

                    }

                    Spacer(modifier = Modifier.height(15.dp))
                    MemberInfo(
                        "member1",
                        memImage = R.drawable.account_circle_48px,
                        name = " Chamath Jayasena "
                    )

                    MemberInfo(
                        "member2",
                        memImage = R.drawable.account_circle_48px,
                        name = " Kumudu Wijewardhana "
                    )

                    MemberInfo(
                        "member3",
                        memImage = R.drawable.account_circle_48px,
                        name = " Thimasha Thakshali  "
                    )
                    MemberInfo(
                        "member4",
                        memImage = R.drawable.account_circle_48px,
                        name = " Ruwindi Perera "
                    )
                    MemberInfo(
                        "member5",
                        memImage = R.drawable.account_circle_48px,
                        name = " Sahan Dissanayake "
                    )
                }

            }
        }

    }
}




@Preview
@Composable
fun AboutUsScreenPreview()
{
    AboutUsScreen()
    
}