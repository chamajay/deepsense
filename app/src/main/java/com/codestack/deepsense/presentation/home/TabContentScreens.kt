package com.codestack.deepsense.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codestack.deepsense.core.Utils

@Composable
fun TodayScreen(
    viewModel: HomeViewModel
) {
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
                Mood(viewModel)
            }
            Row {
                MoodToday(viewModel)
            }
            Row {
                MentalHealth()
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TodayScreenPreview() {
//    TodayScreen()
//}

@Composable
fun WeekScreen(
    viewModel: HomeViewModel
) {
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

//@Preview(showBackground = true)
//@Composable
//fun WeekScreenPreview() {
//    WeekScreen()
//}


@Composable
fun MonthScreen(
    viewModel: HomeViewModel
) {
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

//@Preview(showBackground = true)
//@Composable
//fun MonthScreenPreview() {
//    MonthScreen()
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mood(
    viewModel: HomeViewModel
) {
    val mood by viewModel.mood.collectAsState()
    val moodImg by viewModel.moodImage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.retrieveMood()
    }

    ElevatedCard(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(5.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (mood.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            } else {
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
                        painter = painterResource(id = moodImg),
                        contentDescription = "hello",
                        modifier = Modifier.size(80.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MoodPercentage(
    mood: String,
    percentage: Double,
    image: Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(15.dp)) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "hello",
            modifier = Modifier.size(25.dp)
        )
        Text(
            text = mood,
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.titleMedium.fontSize
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
fun MoodToday(
    viewModel: HomeViewModel
) {
    val moodPercentages by viewModel.emotionsPercentages.collectAsState()
//    val mood by viewModel.mood.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.retrieveMoodPercentages()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {
        Text(
            text = "Your mood today",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 5.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                if (moodPercentages.isEmpty()) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                } else {

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        moodPercentages.forEach { (emotion, percentage) ->
                            val (mappedEmotion, imageResId) = Utils.mapEmotion(emotion)
                            MoodPercentage(
                                mood = mappedEmotion,
                                percentage = percentage,
                                image = imageResId
                            )
                        }
                    }
                }
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
