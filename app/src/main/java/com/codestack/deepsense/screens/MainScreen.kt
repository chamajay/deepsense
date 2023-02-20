package com.codestack.deepsense.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codestack.deepsense.navigation.BottomBarScreen
import com.codestack.deepsense.navigation.BottomNavGraph
import com.codestack.deepsense.screens.home.NoRippleInteractionSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = bottomNavController) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            BottomNavGraph(bottomNavController = bottomNavController, navController = navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Activity,
        BottomBarScreen.Home,
        BottomBarScreen.Suggestions
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            AddNavItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddNavItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        icon = {
            val id =
                if (currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true) screen.iconFilled else screen.icon
            Icon(
                painterResource(id = id),
                contentDescription = "Navigation Icon",
                modifier = Modifier.size(24.dp)
            )
        },
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        interactionSource = NoRippleInteractionSource()
    )
}
