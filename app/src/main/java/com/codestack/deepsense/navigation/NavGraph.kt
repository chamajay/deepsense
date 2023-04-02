package com.codestack.deepsense.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codestack.deepsense.screens.MainScreen
import com.codestack.deepsense.presentation.signup.SignUpScreen
import com.codestack.deepsense.presentation.welcome.WelcomeScreen
import com.codestack.deepsense.presentation.aboutus.AboutUsScreen
import com.codestack.deepsense.presentation.activity.ActivityScreen
import com.codestack.deepsense.presentation.contactus.ContactUsScreen
import com.codestack.deepsense.presentation.home.HomeScreen
import com.codestack.deepsense.presentation.accessibility.PermissionsScreen

import com.codestack.deepsense.presentation.settings.SettingsScreen
import com.codestack.deepsense.presentation.signin.SignInScreen

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
        composable(
            route = Screens.Home.route
        ) {
            HomeScreen()
        }
        composable(
            route = Screens.Activity.route
        ) {
            ActivityScreen()
        }
        composable(
            route = Screens.Signin.route
        ) {
            SignInScreen(navController)
        }
        composable(
            route = Screens.AboutUs.route
        ) {
            AboutUsScreen(navController)
        }
        composable(
            route = Screens.ContactUs.route
        ) {
            ContactUsScreen(navController)
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
        composable(
            route = Screens.Accessibility.route
        ) {
            PermissionsScreen(navController)
        }
    }
}


