package com.codestack.deepsense.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codestack.deepsense.screens.EmptyScreen
import com.codestack.deepsense.presentation.activity.ActivityScreen
import com.codestack.deepsense.presentation.home.HomeScreen
import com.codestack.deepsense.presentation.suggestions.SuggestionScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    bottomNavController: NavHostController,
) {
    NavHost(
        navController = bottomNavController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(
            route = BottomBarScreen.Home.route
        ) {
            HomeScreen()
        }
        composable(
            route = BottomBarScreen.Activity.route
        ) {
            ActivityScreen()
        }
        composable(
            route = BottomBarScreen.Suggestions.route
        ) {
            SuggestionScreen()
        }
    }

}