package com.codestack.deepsense.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codestack.deepsense.R

@Composable
fun TopTabRow() {
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    val titles = listOf("Today", "Week", "Month")
    Row {
        TabRow(selectedTabIndex = selectedIndex) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
    }
}

@Composable
fun Greeting() {
    val user = "Chamath"
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(text = "Hello ", fontSize = MaterialTheme.typography.displaySmall.fontSize)
            Text(
                text = user,
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
            Text(text = "!", fontSize = MaterialTheme.typography.displaySmall.fontSize)
        }
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                Icons.Outlined.AccountCircle,
                contentDescription = "Profile Button",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mood(
    mood: String = "Sad"
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 15.dp)
        ) {
            Text(
                text = "Your mood is ",
//                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            )
            Text(
                text = mood,
//                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_loudly_crying_face),
                contentDescription = "hello",
                modifier = Modifier.size(80.dp)
            )
        }
    }
}


//@Composable
//fun () {
//
//}

@Composable
fun HomeScreen(
//    viewModel: HomeViewModel = hiltViewModel(),
//    navController: NavHostController
) {
    Surface {
        Column(
//            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopTabRow()
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Greeting()
                Mood()
            }

//            Spacer(modifier = Modifier.height(10.dp))
//            Row {
//            }
        }
    }

}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}