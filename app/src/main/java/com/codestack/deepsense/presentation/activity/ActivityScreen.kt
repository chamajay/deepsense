package com.codestack.deepsense.presentation.activity


import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.codestack.deepsense.components.NotEnoughDataLg
import com.codestack.deepsense.components.ServerErrorLg
import java.util.*

@Composable
fun ActivityScreen(
    viewModel: ActivityViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        LaunchedEffect(Unit) {
            viewModel.retrieveTypingActivity()
        }

        val typingActivityList by viewModel.typingActivityList.collectAsState()
        val isConnectionError by viewModel.isConnectionError.collectAsState()
        val isNotEnoughData by viewModel.isNotEnoughData.collectAsState()


        Column(Modifier.fillMaxSize()) {
            //Spacer(modifier = Modifier.padding(15.dp))
            Text(
                text = "Your latest typing activity today",
                modifier = Modifier.padding(15.dp, 15.dp),
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )

            if (isConnectionError) {
                ServerErrorLg()
            } else {
                if (isNotEnoughData) {
                    NotEnoughDataLg()
                } else {
                    if (typingActivityList.isEmpty()) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(50.dp))
                        }
                    } else {
                        LazyColumn() {
                            items(typingActivityList.size) {
                                val item = typingActivityList[it]
                                val text: String = item["text"] as String
                                val predictions: Map<String, String> =
                                    item["predictions"] as Map<String, String>
                                ActivityCard(text = text, predictions = predictions, suicidalRisk = predictions["SuicideRisk"] == "Suicidal")
                                // Check SuicideRisk
                                Log.d("Activity suicidalCheck",
                                    (predictions["SuicideRisk"] == "Suicidal").toString()
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
fun LinearProgressIndicator
            (mood: String, percentage: Float,
             suicidal: Boolean,
             //nonSuicidal : Boolean
) {
    var progress by remember { mutableStateOf(0.1f) }
    val size by animateFloatAsState(
        targetValue = progress,
        tween(durationMillis = 1000, delayMillis = 200, easing = LinearOutSlowInEasing)
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = mood,

                modifier = Modifier
                    .weight(6f)
                    .padding(end = 5.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.W300
                //color = if(suicidal) Color.Red else MaterialTheme.colorScheme.onSurface
            )

            LinearProgressIndicator(
                progress = size,
                modifier = Modifier.weight(7.9f),
                color = if(suicidal) Color.Red else MaterialTheme.colorScheme.onSurface
            )

            LaunchedEffect(key1 = true) {
                progress = percentage / 100
            }


            Text(
                text = percentage.toString().substringBefore(".") + "%",
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 15.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.W300
                //color = if(suicidal) Color.Red else MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(Modifier.height(15.dp))

    }
}

@Composable
fun CustomPopUpDialog(
    onDismiss: () -> Unit,
    text: String,
    predictions: Map<String, String>
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
//            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Box(
                modifier = Modifier
                    .height(520.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        //shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(9.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                // Full typed text
                {
                    Text(
                        text = text,
                        textAlign = TextAlign.Center,

                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),


                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    LazyColumn(contentPadding = PaddingValues(10.dp))
                    {
                        var count =0
                        items(predictions.toList().size - 2) {
                            val prediction = predictions.toList()[it]
                            LinearProgressIndicator(
                                mood = prediction.first,
                                percentage = prediction.second.toFloat(),
                                suicidal = prediction.first == "Suicidal",
                                //nonSuicidal = prediction.first == "Non-suicidal"
                            )
                            count++
                            // Add divider bellow emotions list.
                            if(count == 7){

                                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                    Divider(thickness = 1.5.dp,color = Color.Gray,modifier = Modifier.fillMaxWidth(0.1f))
                                 }
                                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                            }

                        }
                    }

                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .width(200.dp)
                            .clip(RoundedCornerShape(5.dp))
                    ) {
                        Text(
                            text = "Close"
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityCard(
    text: String,
    predictions: Map<String, String>,
    suicidalRisk: Boolean
) {

    val dialogVisibility = remember { mutableStateOf(false) }

    Card(
        onClick = { dialogVisibility.value = true },

        modifier = Modifier
            .size(width = 500.dp, height = 120.dp)
            .padding(start = 20.dp, end = 20.dp, top = 15.dp),
        colors = if(!suicidalRisk) CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background) else CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer) ,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)


    ) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                maxLines = 1,
                //color = Color.White
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    SuggestionChip(
                        onClick = { /* Do something! */ },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor =  MaterialTheme.colorScheme.secondary
                        ),
                        border = SuggestionChipDefaults.suggestionChipBorder(
//                            borderWidth = 1.dp,
                            borderColor = MaterialTheme.colorScheme.secondary
                        ),
                        label = {
                            Text(
                                text = predictions["Primary"]!!,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                        },
                    )
                    Spacer(modifier = Modifier.padding(start = 5.dp))

                    //Log.d("SuicideRisk::::","TRUE")
                    // Suicidal chip
                    if(suicidalRisk){
//                        Log.d("SuicideRisk::::","TRUE")

                        SuggestionChip(
                            onClick = { /* Do something! */ },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor =  MaterialTheme.colorScheme.onError
                            ),
                            border = SuggestionChipDefaults.suggestionChipBorder(
//                                borderWidth = 1.dp,
                                borderColor = MaterialTheme.colorScheme.onError
                            ),
                            label = {
                                Text(
                                    text = "Suicidal",
                                    textAlign = TextAlign.Center,
                                    //color = Color.White
                                )
                            },
                        )
                    }


                    Text(
                        text = "${predictions[predictions["Primary"]]}%",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.weight(6f),
                        //color = Color.White
                    )


                }

            }
        }
    }
    if (dialogVisibility.value) {
        CustomPopUpDialog(
            onDismiss = { dialogVisibility.value = false },
            text,
            predictions
        )
    }
}


@Preview()
@Composable
fun ActivityScreenPreview() {
    ActivityScreen()
}


