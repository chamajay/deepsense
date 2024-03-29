package com.codestack.deepsense.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codestack.deepsense.core.Utils
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
            } else {
                navigateToAccessibilityScreen()
            }
        }
    }

    private fun navigateToMainScreen() = navController.navigate(Screens.Main.route)

    private fun navigateToAccessibilityScreen() =
        navController.navigate(Screens.Accessibility.route)

    private fun isServiceEnabled(): Boolean {
        return Utils.sharedPrefGetValue(applicationContext, "isAccessibilityServiceEnabled") as? Boolean ?: false
    }
}
