package com.codestack.deepsense.navigation

import com.codestack.deepsense.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val iconFilled: Int
) {
    object Activity : BottomBarScreen(
        route = "bottom_activity",
        title = "Activity",
        icon = R.drawable.ic_leaderboard_outlined,
        iconFilled = R.drawable.ic_leaderboard_filled
    )
    object Home : BottomBarScreen(
        route = "bottom_home",
        title = "Home",
        icon = R.drawable.ic_home_outlined,
        iconFilled = R.drawable.ic_home_filled
    )
    object Suggestions : BottomBarScreen(
        route = "bottom_settings",
        title = "Suggestions",
        icon = R.drawable.ic_spa_outlined,
        iconFilled = R.drawable.ic_spa_filled
    )
}
