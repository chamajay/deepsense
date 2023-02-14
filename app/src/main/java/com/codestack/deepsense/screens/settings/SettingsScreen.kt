package com.codestack.deepsense.screens.settings


import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SettingsScreen() {
    Column {
       // HeaderText()
        ProfileUI()
        AccountUI()
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
            fontSize =MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        GeneralItems(
            icon = Icons.Filled.ArrowForward,
            mainText = "Customize",
            subText = "Customize your app",
            onClick = {}
        )
        GeneralItems(
            icon = Icons.Filled.ArrowForward,
            mainText = "Emergency contact",
            subText = "******",
            onClick = {}
        )
        GeneralItems(
            icon = Icons.Filled.ArrowForward,
            mainText = "Backup data",
            subText = "*****",
            onClick = {}
        )
        GeneralItems(
            icon = Icons.Filled.ArrowForward,
            mainText = "Export mood data",
            subText = "*****",
            onClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralItems(
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
                        fontSize =MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = subText,
                        //fontFamily =Poppins,
                        //color = Color.LightGray,
                        fontSize =MaterialTheme.typography.titleMedium.fontSize,
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
fun AccountItems(
    icon:ImageVector,
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
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = subText,
                        //fontFamily =Poppins,
                        //color = Color.LightGray,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
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
fun AccountUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Account",
            //fontFamily = Poppins,
            //color = Color.Black,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        AccountItems(
            icon = Icons.Filled.ArrowForward,
            mainText = "Edit Account",
            subText = "*****",
            onClick = {}
        )
        AccountItems(
            icon = Icons.Filled.ArrowForward,
            mainText = "Delete/Deactivate Account",
            subText = "*****",
            onClick = {}
        )
    }

}






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileItems() {
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
                    text = "User Name",
                    //fontFamily =Poppins,
                    //color = Color.Black,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = "gmail/contact",
                    //fontFamily =Poppins,
                    //color = Color.Black,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                )
                Button(
                    onClick = {},
                    modifier = Modifier.padding(top = 10.dp),
                    //shape = Shapes.medium
                )
                {
                    Text(
                        text = "Sign Out",
                        //fontFamily =Poppins,
                        //color = Color.Black,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
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
fun ProfileUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {

        Text(
            text = "Settings",
            //fontFamily = Poppins,
            //color = Color.Black,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ProfileItems()
    }

        
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
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        SupportItems(
            icon = Icons.Filled.ArrowForward,
            mainText = "Feedback",
            subText = "*****",
            onClick = {}
        )
        SupportItems(
            icon = Icons.Filled.ArrowForward,
            mainText = "About Us",
            subText = "*****",
            onClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportItems
            ( icon:ImageVector,
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
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = subText,
                        //fontFamily =Poppins,
                        //color = Color.LightGray,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
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







