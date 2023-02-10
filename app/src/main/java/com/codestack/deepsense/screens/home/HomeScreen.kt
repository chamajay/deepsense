package com.codestack.deepsense.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codestack.deepsense.R


@Composable
fun TopTabRow() {
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    val titles = listOf("Day", "Week", "Month")
    Row {
        TabRow(selectedTabIndex = selectedIndex) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    interactionSource = NoRippleInteractionSource(),
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
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row() {
            Text(text = "Hello ", fontSize = MaterialTheme.typography.headlineSmall.fontSize)
            Text(
                text = user,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
            Text(text = "!", fontSize = MaterialTheme.typography.headlineSmall.fontSize)
        }
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                Icons.Outlined.AccountCircle,
                contentDescription = "Profile Button",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mood(
    mood: String = "Sad"
) {
    ElevatedCard(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(5.dp),
    ) {
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 15.dp)
            ) {
                Text(
                    text = "You seem ",
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                )
                Text(
                    text = mood,
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
}

@Composable
fun MoodPercentage(
    mood: String,
    percentage: Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(15.dp)) {
        Text(
            text = mood,
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
        Text(
            text = "$percentage%",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )
    }
}

//@Composable
//@Preview
//fun MoodPercentagePreview() {
//    MoodPercentage("Happy", 75)
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodToday() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Your mood today",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                ) {
                MoodPercentage(mood = "Happy", percentage = 30)
                MoodPercentage(mood = "Sad", percentage = 50)
                MoodPercentage(mood = "Angry", percentage = 15)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MentalHealth() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Your mental health",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(0.dp, 5.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            ) {
                Text(
                    text = "You seem healthy!",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    modifier = Modifier.padding(5.dp, 0.dp)
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    var selectedItem by remember { mutableStateOf(1) }
    NavigationBar {
        NavigationBarItem(
            icon = {
                val id =
                    if (selectedItem == 0) R.drawable.ic_leaderboard_filled else R.drawable.ic_leaderboard_outlined
                Icon(
                    painterResource(id = id),
                    contentDescription = "Activity",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Activity") },
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 },
            interactionSource = NoRippleInteractionSource()
        )
        NavigationBarItem(
            icon = {
                val id =
                    if (selectedItem == 1) R.drawable.ic_home_filled else R.drawable.ic_home_outlined
                Icon(
                    painterResource(id = id),
                    contentDescription = "Suggestions",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Home") },
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            interactionSource = NoRippleInteractionSource()
        )
        NavigationBarItem(
            icon = {
                val id =
                    if (selectedItem == 2) R.drawable.ic_spa_filled else R.drawable.ic_spa_outlined
                Icon(
                    painterResource(id = id),
                    contentDescription = "Suggestions",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Suggestions") },
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 },
            interactionSource = NoRippleInteractionSource()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
//    navController: NavHostController
) {
    Scaffold(
        topBar = { TopTabRow() },
        content = {contentPadding ->
            Box(Modifier.padding(contentPadding)) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .padding(15.dp, 0.dp)
                        .fillMaxHeight()
                ) {
                    Row {
                        Greeting()
                    }
                    Row {
                        Mood()
                    }
                    Row {
                        MoodToday()
                    }
                    Row {
                        MentalHealth()
                    }
                }
            }
        },
        bottomBar = { BottomNavigationBar() }
    )
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}