package com.codestack.deepsense.presentation.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

typealias ComposableFun = @Composable (HomeViewModel) -> Unit

// https://johncodeos.com/how-to-create-tabs-with-jetpack-compose/
sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Today : TabItem("Today", { homeViewModel -> TodayScreen(homeViewModel) })
    object Week : TabItem("Week", { homeViewModel -> WeekScreen(homeViewModel) })
    object Month : TabItem("Month", { homeViewModel -> MonthScreen(homeViewModel) })
}
