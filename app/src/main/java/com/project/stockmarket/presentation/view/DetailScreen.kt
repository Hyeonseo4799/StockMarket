package com.project.stockmarket.presentation.view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.stockmarket.domain.model.StockPriceInfo
import com.project.stockmarket.presentation.utils.DataState
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

    viewModel.getKoreaStandardIndustryCode()
    viewModel.getStockPriceInfo(isinCode)
    viewModel.getCorporationInfo(corpNumber)
    viewModel.getStockIssuanceInfo(corpNumber)

    if (corporationInfoState.data.isNotEmpty()) {
        val industryCode = koreaStandardIndustryCodeState.data.filter {
            it.industryCode == corporationInfoState.data[0].IndustryCode
        }
        if (industryCode.isNotEmpty())
            CardView(stockPriceInfoState, industryCode[0].industryClassification, navController)
    }
    StateView(state = listOf(corporationInfoState, stockPriceInfoState))
}

@Composable
fun CardView(
    stockPriceInfoState: DataState<StockPriceInfo>,
    industryCode: String,
    navController: NavController
) {
    if (stockPriceInfoState.data.isNotEmpty()) {
        TopBar(text = stockPriceInfoState.data[0].stockName, content = {
            Content(stockPriceInfoState.data[0], industryCode)
        }) {
            navController.popBackStack()
        }
    }
}