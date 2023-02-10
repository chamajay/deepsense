package com.codestack.deepsense.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codestack.deepsense.screens.signup.SignUpScreen
import com.codestack.deepsense.screens.WelcomeScreen
import com.codestack.deepsense.screens.activity.ActivityScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Activity.route
    ) {
        composable(
            route = Screens.Welcome.route
        ) {
            WelcomeScreen(navController)
        }
        composable(
            route = Screens.SignUp.route
        ) {
            SignUpScreen(navController)
        }

        composable(
            route = Screens.Activity.route
        ) {
            ActivityScreen()
        }
    }
}