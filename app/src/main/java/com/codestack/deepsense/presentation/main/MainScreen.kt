package com.codestack.deepsense.screens

import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codestack.deepsense.components.MainTopAppBar
import com.codestack.deepsense.navigation.BottomBarScreen
import com.codestack.deepsense.navigation.BottomNavGraph
import com.codestack.deepsense.presentation.home.NoRippleInteractionSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.SEND_SMS),
                123
            )
        }
    }

    val bottomNavController = rememberNavController()
    Scaffold(
        topBar = { MainTopAppBar(navController = navController) },
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
            val iconTint = if (currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onPrimaryContainer
            Icon(
                painterResource(id = id),
                contentDescription = "Navigation Icon",
                modifier = Modifier.size(24.dp),
                tint = iconTint
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
