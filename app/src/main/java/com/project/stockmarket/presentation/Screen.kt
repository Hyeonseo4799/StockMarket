package com.project.stockmarket.presentation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object SearchScreen: Screen("search_screen")
    object DetailScreen: Screen("detail_screen")
}
