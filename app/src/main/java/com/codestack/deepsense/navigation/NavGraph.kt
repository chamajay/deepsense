package com.codestack.deepsense.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codestack.deepsense.screens.signup.SignUpScreen
import com.codestack.deepsense.screens.WelcomeScreen
import com.codestack.deepsense.screens.aboutus.AboutUsScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.AboutUs.route
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
            route = Screens.AboutUs.route
        ) {
            AboutUsScreen()
        }
    }
}


