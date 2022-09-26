package com.project.stockmarket.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.stockmarket.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    isinCode: String,
    corpNumber: String,
    navController: NavController
) {
    val corporationInfoState = viewModel.corporationInfoState.value
    val stockPriceInfoState = viewModel.stockPriceInfoState.value
    val stockIssuanceInfoState = viewModel.stockIssuanceInfoState.value
    val koreaStandardIndustryCodeState = viewModel.koreaStandardIndustryCodeState.value

    viewModel.getStockPriceInfo(isinCode)
    viewModel.getCorporationInfo(corpNumber)
    viewModel.getStockIssuanceInfo(corpNumber)
    viewModel.getKoreaStandardIndustryCode()

    Box(modifier = Modifier.fillMaxSize()) {
        if (stockPriceInfoState.data.isNotEmpty()) {
            TopBar(text = stockPriceInfoState.data[0].stockName) {
                navController.popBackStack()
            }
        }
    }
}