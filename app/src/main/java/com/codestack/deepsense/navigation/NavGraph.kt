package com.codestack.deepsense.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codestack.deepsense.screens.signup.SignUpScreen
import com.codestack.deepsense.screens.WelcomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Welcome.route
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
    }
}