package com.codestack.deepsense.presentation.home

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

// https://johncodeos.com/how-to-create-tabs-with-jetpack-compose/
sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Today : TabItem("Today", { TodayScreen() })
    object Week : TabItem("Week", { WeekScreen() })
    object Month : TabItem("Month", { MonthScreen() })
}
