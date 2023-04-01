package com.codestack.deepsense.presentation.home

import android.graphics.drawable.AnimatedImageDrawable
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.codestack.deepsense.R
import com.codestack.deepsense.components.NotEnoughDataLg
import com.codestack.deepsense.components.NotEnoughDataSm
import com.codestack.deepsense.components.ServerErrorLg
import com.codestack.deepsense.components.ServerErrorSm
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
                MentalHealth(viewModel)
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
    val isConnectionError by viewModel.isConnectionError.collectAsState()
    val isNotEnoughData by viewModel.isNotEnoughData.collectAsState()


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
fun MoodToday(
    viewModel: HomeViewModel
) {
    val moodPercentages by viewModel.emotionsPercentages.collectAsState()
    val isConnectionError by viewModel.isConnectionError.collectAsState()
    val isNotEnoughData by viewModel.isNotEnoughData.collectAsState()

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
    viewModel: HomeViewModel
) {
    val isConnectionError by viewModel.isConnectionError.collectAsState()
    val isNotEnoughData by viewModel.isNotEnoughData.collectAsState()


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
                if (isConnectionError) {
                    ServerErrorSm()
                } else {
                    if (isNotEnoughData) {
                        NotEnoughDataSm()
                    } else {
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