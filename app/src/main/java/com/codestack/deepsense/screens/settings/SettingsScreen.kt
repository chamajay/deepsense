package com.codestack.deepsense.screens.settings


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SettingsScreen() {
    Column {
        HeaderText()
        ProfileUI()
        GeneralUI()
        SupportUI()

    }
}

@Preview(showBackground =true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}



@Composable
fun SupportUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Support",
            //fontFamily = Poppins,
            //color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        SupportItems(
            icon = Icons.Filled.ArrowForward,
            mainText = "Contact",
            subText = "Contact Details",
            onClick = {}
        )
        SupportItems(
            icon = Icons.Filled.ArrowForward,
            mainText = "FeedBack",
            subText = "******",
            onClick = {}
        )
        SupportItems(
            icon = Icons.Filled.ArrowForward,
            mainText = "About Us",
            subText = "Abouttttt uss",
            onClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportItems(
    icon: ImageVector,
    mainText: String,
    subText: String,
    onClick: () -> Unit
) {
    Card(


        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),

        ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .size(34.dp)
                        //.clip(shape = Shapes.medium)
                        //.background(Color.White)
                ) {
                    Icon(
                        icon,
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(8.dp)

                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = mainText,
                        //fontFamily =Poppins,
                        //color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = subText,
                        //fontFamily =Poppins,
                        //color = Color.LightGray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.offset(y = (-4).dp)
                    )
                }
            }
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}


@Composable
fun GeneralUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {

        Text(
            text = "General",
            //fontFamily = Poppins,
            //color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        GeneralUIitems()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralUIitems() {
    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),

        ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .size(34.dp)
                        //.clip(shape = Shapes.medium)
                        //.background(Color.White)
                ) {
                    Icon(
                        Icons.Filled.ArrowForward,
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(8.dp)

                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = "Customize",
                        //fontFamily =Poppins,
                        //color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Customize your app",
                        //fontFamily =Poppins,
                        //color = Color.LightGray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.offset(y = (-4).dp)
                    )
                }
            }
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileUI() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
        //shape= Shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "My Profile",
                    //fontFamily =Poppins,
                    //color = Color.Black,
                    fontSize =MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = "View more details of your profile",
                    //fontFamily =Poppins,
                    //color = Color.Black,
                    fontSize = 10.sp,
                )
                Button(
                    onClick = {},
                    modifier = Modifier.padding(top = 10.dp),


                    //shape = Shapes.medium
                )
                {
                    Text(
                        text = "View",
                        //fontFamily =Poppins,
                        //color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold

                    )

                }
            }

            Image(
                Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier.height(120.dp)

            )
        }

    }
}

@Composable
fun HeaderText() {
    Text(
        text = "Settings",
        //fontFamily=Poppins,
        //color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 10.dp),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    )
}

