package com.project.stockmarket.presentation.view

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.stockmarket.presentation.viewmodel.CorporationInfoViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    crno: String?,
    viewModel: CorporationInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    crno?.let { viewModel.getCorporationInfo(it) }
}