package com.codestack.deepsense.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
//    viewModel: HomeViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState()
    val tabs = listOf(TabItem.Today, TabItem.Week, TabItem.Month)

    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        TabsRow(tabs, pagerState)
        TabsContent(tabs, pagerState)
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsRow(
    tabs: List<TabItem>,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    TabRow(
        // Our selected tab is our current page
        selectedTabIndex = pagerState.currentPage,
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size, userScrollEnabled = false) { page ->
        tabs[page].screen()
    }
}
