package com.codestack.deepsense.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codestack.deepsense.screens.MainScreen
import com.codestack.deepsense.screens.signup.SignUpScreen
import com.codestack.deepsense.screens.WelcomeScreen
import com.codestack.deepsense.screens.aboutus.AboutUsScreen
import com.codestack.deepsense.screens.activity.ActivityScreen
import com.codestack.deepsense.screens.contactus.ContactUsScreen
import com.codestack.deepsense.screens.home.HomeScreen
import com.codestack.deepsense.screens.login.LoginScreen
import com.codestack.deepsense.screens.settings.SettingsScreen

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
//        composable(
//            route = Screens.Home.route
//        ) {
//            HomeScreen(navController = navController, paddingValues = )
//        }
        composable(
            route = Screens.Activity.route
        ) {
            ActivityScreen()
        }
        composable(
            route = Screens.Login.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = Screens.AboutUs.route
        ) {
            AboutUsScreen()
        }
        composable(
            route = Screens.ContactUs.route
        ) {
            ContactUsScreen()
        }
        composable(
            route = Screens.Settings.route
        ) {
            SettingsScreen(navController)
        }
        composable(
            route = Screens.Main.route
        ) {
            MainScreen(navController)
        }
    }
}


