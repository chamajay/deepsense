package com.codestack.deepsense.presentation.settings


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codestack.deepsense.R
import com.codestack.deepsense.navigation.Screens
import com.codestack.deepsense.presentation.shared.ProfileViewModel


@Composable
fun SettingsScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Surface {
        Column {

            LazyColumn {
                item { ProfileUI(viewModel.displayName,viewModel.photoUrl) }
                item { GeneralUI() }
                item { AccountUI() }
                item { SupportUI(navController) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileUI(name: String, imageUrl:String?) {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "Settings",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(2.dp),
            //shape= Shapes.medium
        ) {
            Row(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = name,
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
                        modifier = Modifier.padding(top =2.dp),
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)

                ) {


                }

                Image(
                    painter = painterResource(R.drawable.account_circle_48px),
                    contentDescription = "",
                    modifier = Modifier.height(150.dp)

                )
            }

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
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        GeneralItems(
            icon = R.drawable.settings_account_box_48px,
            mainText = "Customize your app ",
            //subText = "Customize your app",
            onClick = {},
        )
        GeneralItems(
            icon = R.drawable.contact_emergency_48px,
            mainText = "Emergency contact",
            //subText = "Contact details of ",
            onClick = {}
        )
        GeneralItems(
            icon = R.drawable.backup_48px,
            mainText = "Backup data",
            //subText = "*****",
            onClick = {},

            )
        GeneralItems(
            icon = R.drawable.ios_share_48px,
            mainText = "Export mood data",
            //subText = "*****",
            onClick = {},
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralItems(
    icon: Int,
    mainText: String,
    //subText: String,
    onClick: () -> Unit
) {
    Card(
        onClick = {
            onClick()
        },

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
                        painterResource(id = icon),
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

                        )
//                    Text(
//                        text = subText,
//                        //fontFamily =Poppins,
//                        //color = Color.LightGray,
//                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
//                        fontWeight = FontWeight.SemiBold,
//                        modifier = Modifier.offset(y = (-4).dp)
//                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)

            ) {

            }
            Icon(
                painter = painterResource(id = R.drawable.arrow_forward_ios_48px),
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Account",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Card(
            onClick = {},
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
                    ) {
                        Icon(
                            painterResource(id = R.drawable.delete_48px),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = Modifier.padding(8.dp)

                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = "Delete Account",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)

                ) {

                }
                Icon(
                    painter = painterResource(id = R.drawable.arrow_forward_ios_48px),
                    contentDescription = "",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}




@Composable
fun SupportUI(navController: NavHostController) {
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
            icon = R.drawable.rate_review_48px,
            mainText = "Contact Us",
            //subText = "*****",
            onClick = { navController.navigate(Screens.ContactUs.route) }
        )
        SupportItems(
            icon = R.drawable.group_48px,
            mainText = "About Us",
            //subText = "*****",
            onClick = { navController.navigate(Screens.AboutUs.route) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportItems(
    icon: Int,
    mainText: String,
    //subText: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,

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
                        painterResource(id = icon),
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
                        //fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)

            ) {

            }
            Icon(
                painter = painterResource(id = R.drawable.arrow_forward_ios_48px),
                contentDescription = "",
                modifier = Modifier.size(16.dp)

            )
        }
    }


}