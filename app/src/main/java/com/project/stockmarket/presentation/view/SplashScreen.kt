package com.project.stockmarket.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.stockmarket.presentation.Screen
import com.project.stockmarket.presentation.viewmodel.MainViewModel

@Composable
fun SplashScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.industryCodeState.value
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Splash")

        if (state.data.isNotEmpty()) {
            LaunchedEffect(Unit) {
                viewModel.insertIndustryCode(state.data)
                navController.navigate(Screen.SearchScreen.route)
            }
        }
    }
}