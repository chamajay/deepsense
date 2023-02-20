package com.codestack.deepsense.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codestack.deepsense.screens.EmptyScreen
import com.codestack.deepsense.screens.activity.ActivityScreen
import com.codestack.deepsense.screens.home.HomeScreen

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
            HomeScreen(navController = navController)
        }
        composable(
            route = BottomBarScreen.Activity.route
        ) {
            ActivityScreen()
        }
        composable(
            route = BottomBarScreen.Suggestions.route
        ) {
            EmptyScreen()
        }
    }

}