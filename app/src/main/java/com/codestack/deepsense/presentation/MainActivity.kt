package com.codestack.deepsense.presentation

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codestack.deepsense.navigation.Screens
import com.codestack.deepsense.navigation.SetupNavGraph
import com.codestack.deepsense.presentation.shared.ProfileViewModel
import com.codestack.deepsense.ui.theme.DeepSenseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide actionbar
        actionBar?.hide()
        setContent {
            DeepSenseTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
            reroute()
        }
    }

    private fun reroute() {
        if (viewModel.isUserAuthenticated) {
            if (isServiceEnabled()) {
                navigateToMainScreen()
                requestSmsPermission()
            } else {
                navigateToAccessibilityScreen()
            }
        }
    }

    private fun navigateToMainScreen() = navController.navigate(Screens.Main.route)

    private fun navigateToAccessibilityScreen() =
        navController.navigate(Screens.Accessibility.route)

    private fun isServiceEnabled(): Boolean {
        val sharedPreference =
            applicationContext.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        return sharedPreference.getBoolean("isAccessibilityServiceEnabled", false)
    }

    private fun requestSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.SEND_SMS),
                123
            )
        }
    }
}
