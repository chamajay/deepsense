package com.codestack.deepsense.presentation.settings


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codestack.deepsense.R
import com.codestack.deepsense.navigation.Screens
import com.codestack.deepsense.presentation.shared.ProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Settings")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(18.dp)) {
                LazyColumn {
                    item {
                        ProfileUI(
                            viewModel
                        )
                    }
                    item { GeneralUI(navController) }
                    item { SupportUI(navController) }
                    item { AccountUI(viewModel) }
                }
            }
        }
    }

    SignOut(
        navigateToAuthScreen = { signedOut ->
            if (signedOut) {
                navController.navigate(Screens.Signin.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
    )
}


//@Preview(showBackground = true)
//@Composable
//fun SettingsScreenPreview() {
//    SettingsScreen(navController = rememberNavController())
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileUI(viewModel: ProfileViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(15.dp)) {
            if (viewModel.photoUrl != "null") {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(viewModel.photoUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(80.dp)
                )
            } else {
                Icon(
                    Icons.Outlined.AccountCircle,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(70.dp)
                )
            }
            Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                Text(
                    viewModel.displayName,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
                if (viewModel.email != null) {
                    Text(viewModel.email!!)
                }
            }
        }
    }
}


@Composable
fun GeneralUI(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
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
            icon = R.drawable.contact_emergency_48px,
            mainText = "Emergency contact",
            //subText = "Contact details of ",
            onClick = { navController.navigate(Screens.EmergencyContact.route) }
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


@Composable
fun AccountUI(viewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Account",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { viewModel.signOut() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign Out")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    viewModel.revokeAccess() },
                modifier = Modifier.fillMaxWidth(),
//                border = BorderStroke(1.dp, MaterialTheme.colorScheme.errorContainer)
                colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Text(text = "Delete Account", color = MaterialTheme.colorScheme.onErrorContainer)
            }
        }
    }
}


@Composable
fun SupportUI(navController: NavHostController) {
    Column(
        modifier = Modifier
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


