package com.codestack.deepsense.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.codestack.deepsense.components.NotEnoughDataLg
import com.codestack.deepsense.components.NotEnoughDataSm
import com.codestack.deepsense.components.ServerErrorLg
import com.codestack.deepsense.components.ServerErrorSm
import com.codestack.deepsense.core.Utils

@Composable
fun TodayScreen(
    viewModel: HomeViewModel
) {
    val mood by viewModel.mood.collectAsState()
    val moodImg by viewModel.moodImage.collectAsState()
    val moodPercentages by viewModel.emotionsPercentages.collectAsState()
    val isConnectionError by viewModel.isConnectionError.collectAsState()
    val isNotEnoughData by viewModel.isNotEnoughData.collectAsState()

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
                Mood(mood, moodImg, isConnectionError, isNotEnoughData)
            }
            Row {
                MoodPercentages(
                    moodPercentages,
                    isConnectionError,
                    isNotEnoughData,
                    "Your mood today"
                )
            }
            Row {
                MentalHealth(true, mood, isConnectionError, isNotEnoughData)
            }
        }
    }
}


@Composable
fun WeekScreen(
    viewModel: HomeViewModel
) {
    val moodWeek by viewModel.moodWeek.collectAsState()
    val moodWeekImg by viewModel.moodWeekImage.collectAsState()
    val moodWeekPercentages by viewModel.emotionsPercentagesWeek.collectAsState()
    val isConnectionError by viewModel.isConnectionError.collectAsState()
    val isNotEnoughData by viewModel.isNotEnoughData.collectAsState()

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
                Mood(moodWeek, moodWeekImg, isConnectionError, isNotEnoughData)
            }
            Row {
                MoodPercentages(
                    moodWeekPercentages,
                    isConnectionError,
                    isNotEnoughData,
                    "Your mood this week"
                )
            }
            Row {
                MentalHealth(false, moodWeek, isConnectionError, isNotEnoughData)
            }
        }
    }
}


@Composable
fun MonthScreen(
    viewModel: HomeViewModel
) {
    val moodMonth by viewModel.moodMonth.collectAsState()
    val moodMonthImg by viewModel.moodMonthImage.collectAsState()
    val moodMonthPercentages by viewModel.emotionsPercentagesMonth.collectAsState()
    val isConnectionError by viewModel.isConnectionError.collectAsState()
    val isNotEnoughData by viewModel.isNotEnoughData.collectAsState()

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
                Mood(moodMonth, moodMonthImg, isConnectionError, isNotEnoughData)
            }
            Row {
                MoodPercentages(
                    moodMonthPercentages,
                    isConnectionError,
                    isNotEnoughData,
                    "Your mood this month"
                )
            }
            Row {
                MentalHealth(false, moodMonth, isConnectionError, isNotEnoughData)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mood(
    mood: String,
    moodImg: Int,
    isConnectionError: Boolean,
    isNotEnoughData: Boolean
) {

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
            if (isConnectionError) {
                ServerErrorLg()
            } else {
                if (isNotEnoughData) {
                    NotEnoughDataLg()
                } else {
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
                            LottieAnimation(lottieFile = moodImg)
                        }
                    }
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 15.dp, horizontal = 25.dp)
    ) {
        Row(modifier = Modifier.size(35.dp)) {
            LottieAnimation(lottieFile = image)
        }
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
fun MoodPercentages(
    moodPercentages: MutableMap<String, Double>,
    isConnectionError: Boolean,
    isNotEnoughData: Boolean,
    title: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {
        Text(
            text = title,
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
                if (isConnectionError) {
                    ServerErrorLg()
                } else {
                    if (isNotEnoughData) {
                        NotEnoughDataSm()
                    } else {
                        if (moodPercentages.isEmpty()) {
                            CircularProgressIndicator(modifier = Modifier.size(50.dp))
                        } else {
                            LazyRow(
                                contentPadding = PaddingValues(
                                    horizontal = 16.dp
                                ),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(moodPercentages.size) { index ->
                                    val emotion = moodPercentages.keys.toList()[index]
                                    val percentage = moodPercentages.values.toList()[index]
                                    val (mappedEmotion, imageResId) = Utils.mapEmotion(emotion)
                                    Box(
                                        modifier = Modifier.wrapContentSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
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
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MentalHealth(
    isToday: Boolean = false,
    mood: String,
    isConnectionError: Boolean,
    isNotEnoughData: Boolean
) {

    val isDepressed = mood.equals("sad", ignoreCase = true)

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Your mental health",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        ElevatedCard(
            colors = if (isDepressed) CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.errorContainer) else CardDefaults.elevatedCardColors(),
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
                if (isConnectionError) {
                    ServerErrorSm()
                } else {
                    if (isNotEnoughData) {
                        NotEnoughDataSm()
                    } else {
                        if (mood.isEmpty()) {
                            CircularProgressIndicator(modifier = Modifier.size(30.dp))
                        } else {
                            if (isToday) {
                                Text(
                                    text = "You seem Healthy!",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    modifier = Modifier.padding(5.dp, 0.dp)
                                )
                            } else {
                                Text(
                                    text = if (isDepressed) "You may be Depressed!" else "You seem Healthy!",
                                    color = if (isDepressed) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.primary,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    modifier = Modifier.padding(5.dp, 0.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun LottieAnimation(lottieFile: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieFile))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
        speed = 0.8f
    )
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}