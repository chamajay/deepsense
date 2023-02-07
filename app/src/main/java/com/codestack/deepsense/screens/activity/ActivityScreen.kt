package com.codestack.deepsense.screens.activity


import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.codestack.deepsense.ui.theme.DeepSenseTheme

import java.util.*

@Composable
fun ActivityScreen() {

    val moodList = arrayOf("Joy", "Suicidal", "Sadness", "Admiration", "Neutral")
    val moodStatusList = arrayOf(
        "I feel lucky to have a friend like you",
        "I hate this life, I feel like ending it all",
        "My dog passed away today",
        "Good things are coming my way",
        "Nothing is new, I need something different and get new experience "
    )

    val percentageList = arrayOf("80", "99", "95", "85", "56")
    val allValues = arrayOf(moodStatusList, moodList, percentageList)

    val valueLength = moodList.size + moodStatusList.size + percentageList.size
    Log.d("Total length : ", valueLength.toString())


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        //modifier = Modifier.scrollable(state =scrollState,orientation = Orientation.Horizontal )
        Column(Modifier.fillMaxSize()) {
            TopBar(title = "Activity page", imageVector = Icons.Default.AccountCircle)
            Spacer(modifier = Modifier.padding(15.dp))
            MoodScoreSection()
            MoodTitle(percentage = "85")

            Text(
                text = "Your typing activity",
                modifier = Modifier.padding(20.dp),
                fontSize = 20.sp
            )

//                        Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(bottom = 20.dp)) {
//                            ActivityCard("I feel lucky to have a friend like you", "Joy","80")
//                            ActivityCard("I hate this life, I feel like ending it all","Suicidal","99")
//                            ActivityCard("My dog passed away today","Sadness","95")
//                            ActivityCard("Good things are coming my way","Admiration","85")
//                        }

            LazyColumn(
                contentPadding = PaddingValues(10.dp),

                )
            {
                itemsIndexed(items = allValues[0]) { index, item ->

                    Log.d("Current item : ", item.toString())
                    Log.d("CurrentIndex : ", index.toString())

                    //Card component
                    ActivityCard(

                        moodStatus = moodStatusList[index],
                        emotion = moodList[index],
                        percentage = percentageList[index],
                    )

//                                for(i in 0..allValues.size){
//                                    ActivityCard(
//
//                                        moodStatus = allValues[0][index],
//                                        emotion = allValues[1][index],
//                                        percentage = allValues[2][index],
//                                    )
//                                }


                    //ActivityCard("Good things are coming my way","Admiration","85")
                }
            }
        }
    }
}


@Composable
fun LinearProgressIndicator() {

    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Joy", modifier = Modifier.weight(2f))
            LinearProgressIndicator(progress = 0.2f, modifier = Modifier.weight(7f))
        }
        Spacer(Modifier.height(15.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Sad", modifier = Modifier.weight(2f))
            LinearProgressIndicator(progress = 0.5f, modifier = Modifier.weight(7f))
        }

        Spacer(Modifier.height(15.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Neutral", modifier = Modifier.weight(2f))
            LinearProgressIndicator(progress = 0.7f, modifier = Modifier.weight(7f))
        }
        Spacer(Modifier.height(15.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Joy", modifier = Modifier.weight(2f))
            LinearProgressIndicator(progress = 0.9f, modifier = Modifier.weight(7f))
        }

    }
}

@Composable
fun CustomPopUpDialog(onDismiss: () -> Unit, title: String, desc: String) {

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .height(560.dp)
        ) {
            Column(modifier = Modifier)
            {
                Spacer(modifier = Modifier.height(130.dp))
                Box(
                    modifier = Modifier
                        .height(390.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = desc,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        LinearProgressIndicator()

                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(5.dp))
                        ) {
                            Text(
                                text = "Close",
                                color = Color.White
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
fun ActivityCard(moodStatus: String, emotion: String, percentage: String) {

    val dialogVisibility = remember { mutableStateOf(false) }

    Card(
        onClick = { dialogVisibility.value = true },

        modifier = Modifier
            .size(width = 500.dp, height = 120.dp)
            .padding(start = 20.dp, end = 20.dp, top = 15.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)


    ) {
        Column(
            modifier = Modifier.padding(10.dp),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = moodStatus,
                fontSize = 18.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.padding(5.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()


            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                )
                {
                    SuggestionChip(
                        onClick = { /* Do something! */ },
                        label = { Text(text = emotion, textAlign = TextAlign.Center) },
                        modifier = Modifier.weight(2f)

                    )
                    //Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "${percentage}%",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.weight(6f)

                    )
                }
            }


        }
    }
    if (dialogVisibility.value) {
        CustomPopUpDialog(onDismiss = { dialogVisibility.value = false }, emotion, moodStatus)
    }
}

@Composable
fun TopBar(title: String, imageVector: ImageVector) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),

        )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            Text(
                text = title,
                fontFamily = FontFamily.SansSerif,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(6f)
            )

            IconButton(
                onClick = {
                    Toast.makeText(context, "Account Circle", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.weight(1f),
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            }

        }
    }
}

@Composable
fun MoodScoreSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, bottom = 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            Text(
                text = "78%",
                fontSize = 50.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,

                )
            Text(
                text = "Your mood score",
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 10.dp, start = 8.dp)

            )
        }
    }
}


@Composable
fun MoodTitle(percentage: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, bottom = 5.dp, top = 5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Keep your score above ${percentage}% to reach your daily goal.",
                fontSize = 20.sp,
                lineHeight = 26.sp,
                color = androidx.compose.ui.graphics.Color.Gray,
                //modifier = Modifier.padding(30.dp, top = 1.dp)
            )
        }
    }

}


@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)

@Composable

fun DefaultView() {
    DeepSenseTheme {
//        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
//
//            Column {
//                TopBar(title = "Activity page", imageVector = Icons.Default.AccountCircle)
//                Spacer(modifier = Modifier.padding(15.dp))
//                MoodScoreSection()
//                MoodTitle(percentage= "85")
//
//            }
//
//        }

//        Column()
//        {
//            TopBar(title = "Activity page", imageVector = Icons.Default.AccountCircle)
//            Spacer(modifier = Modifier.padding(15.dp))
//            MoodScoreSection()
//            MoodTitle(percentage= "85")
//        }

        //ActivityCard(moodStatus = "I feel lucky to have a friend like you", "Joy","76")

        CustomPopUpDialog(
            onDismiss = {},
            "Mood status",
            "Dialog description will be visible here, Some long text should be there"
        )
    }
}