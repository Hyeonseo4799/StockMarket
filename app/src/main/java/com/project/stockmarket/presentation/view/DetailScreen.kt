package com.project.stockmarket.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.stockmarket.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    isinCode: String,
    corpNumber: String
) {
    val corporationInfoState = viewModel.corporationInfoState.value
    val stockPriceInfoState = viewModel.stockPriceInfoState.value
    val stockIssuanceInfoState = viewModel.stockIssuanceInfoState.value
    val koreaStandardIndustryCodeState = viewModel.koreaStandardIndustryCodeState.value

    viewModel.getStockPriceInfo(isinCode)
    viewModel.getCorporationInfo(corpNumber)
    viewModel.getStockIssuanceInfo(corpNumber)
    viewModel.getKoreaStandardIndustryCode()

    Column {
        Text(text = corporationInfoState.data.toString())
        Text(text = stockPriceInfoState.data.toString())
        Text(text = stockIssuanceInfoState.data.toString())
        Text(text = koreaStandardIndustryCodeState.data.toString())
    }
}