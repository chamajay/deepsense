package com.codestack.deepsense.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(15.dp, 0.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Row {
            TopTabRow()
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


@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}