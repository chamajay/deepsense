package com.codestack.deepsense.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codestack.deepsense.navigation.SetupNavGraph
import com.codestack.deepsense.ui.theme.DeepSenseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide actionbar
        actionBar?.hide()
        setContent {
            DeepSenseTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}