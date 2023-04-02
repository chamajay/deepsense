package com.codestack.deepsense.presentation.accessibility

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.codestack.deepsense.R
import com.codestack.deepsense.navigation.Screens


@SuppressLint("ServiceCast")
@Composable
fun PermissionsScreen(
    navController: NavHostController
) {

    val context = LocalContext.current
    val resultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val sharedPreference =
            context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val serviceEnabled = sharedPreference.getBoolean("isAccessibilityServiceEnabled", false)
        if (serviceEnabled) {
            navController.navigate(Screens.Main.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                "Enable",
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                color = Color(0xFF00BC31)
            )
            Text(
                text = "Accessibility Service",
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Column(modifier = Modifier.size(250.dp)) {
                    LottieAnimation(lottieFile = R.raw.key_unlock_lottie)
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "To capture system-wide typed text, DeepSense needs it's accessibility service.",
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    "Ensuring user privacy is a top priority for our app, and we take it very seriously. We want to reassure you that we will not send your user data to third parties or spy on you in any way.",
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text("Please enable the accessibility service to start using the app.")
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Button(onClick = {
                    resultLauncher.launch(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                }) {
                    Text(text = "Enable Accessibility Service")
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PermissionsScreenPreview() {
//    PermissionsScreen()
//}

@Composable
fun LottieAnimation(lottieFile: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieFile))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 0.8f
    )
    com.airbnb.lottie.compose.LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}