package com.project.stockmarket.presentation.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
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

    viewModel.getStockPriceInfo(isinCode)
    viewModel.getCorporationInfo(corpNumber)
    viewModel.getStockIssuanceInfo(corpNumber)

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.background
            ) {
                if (stockPriceInfoState.data.isNotEmpty())
                    TopBarContent(text = stockPriceInfoState.data[0].stockName, navController = navController)
            }
        }
    ) {
        StateView(state = listOf(corporationInfoState, stockPriceInfoState))
        val industryCode = viewModel.getIndustryClassification()
        if (stockPriceInfoState.data.isNotEmpty() && industryCode != null)
            CardView(stockPriceInfo = stockPriceInfoState.data[0], industryCode = industryCode.industryClassification)
    }
}
