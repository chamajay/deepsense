package com.codestack.deepsense.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.codestack.deepsense.ui.theme.DeepSenseTheme
import kotlinx.coroutines.delay

@Composable
fun WelcomeContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Welcome to ",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "DeepSense",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "!",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Button(
            colors = ButtonDefaults.buttonColors(),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Start",
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = "Start",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
    }
}


@Composable
fun FactsContent() {
    val facts = listOf(
        "Depression is a common mental disorder. Globally, it is estimated that 5% of adults suffer from depression.",
        "In middle-aged people and older adults, depression is likely to occur alongside other serious illnesses.",
        "You can reduce your feelings of depression with certain lifestyle changes.",
        "The global economy loses about US \$1 trillion per year in productivity due to depression and anxiety.",
        "Depression is treatable and most people can recover with proper care."
    )
    val randomFact = remember { mutableStateOf(facts.random()) }
    val factVisible = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            factVisible.value = true
            delay(12000)
            factVisible.value = false
            delay(700)
            randomFact.value = facts.random()
            delay(700)
        }
    }
    Box(
        modifier = Modifier.padding(start = 30.dp, bottom = 30.dp, end = 30.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Did You Know?",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                modifier = Modifier.padding(bottom = 10.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
            ) {
                AnimatedVisibility(
                    visible = factVisible.value,
                    enter = fadeIn(
                        animationSpec = tween(700)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(700)
                    )
                ) {
                    Text(
                        text = randomFact.value,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        textAlign = TextAlign.Center,
                        color = Color.Gray,
                        lineHeight = 1.2.em
                    )
                }
            }
        }
    }
}


@Composable
fun WelcomeScreen() {
    Surface (
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            WelcomeContent()
            FactsContent()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    DeepSenseTheme {
        WelcomeScreen()
    }
}
