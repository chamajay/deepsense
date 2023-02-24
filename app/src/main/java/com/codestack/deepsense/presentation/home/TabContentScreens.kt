package com.codestack.deepsense.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codestack.deepsense.R

@Composable
fun TodayScreen() {
    Row(
        modifier = Modifier
            .padding(15.dp, 0.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
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
}

@Preview(showBackground = true)
@Composable
fun TodayScreenPreview() {
    TodayScreen()
}

@Composable
fun WeekScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Week View",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeekScreenPreview() {
    WeekScreen()
}


@Composable
fun MonthScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Month View",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MonthScreenPreview() {
    MonthScreen()
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
                androidx.compose.material3.Text(
                    text = "You seem ",
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                )
                androidx.compose.material3.Text(
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
        androidx.compose.material3.Text(
            text = mood,
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
        androidx.compose.material3.Text(
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
        androidx.compose.material3.Text(
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
        androidx.compose.material3.Text(
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
                androidx.compose.material3.Text(
                    text = "You seem healthy!",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    modifier = Modifier.padding(5.dp, 0.dp)
                )
            }
        }
    }
}
