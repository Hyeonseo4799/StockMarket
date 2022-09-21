package com.project.stockmarket.presentation.view

import android.util.Log
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

    viewModel.getStockPriceInfo(isinCode)
    viewModel.getCorporationInfo(corpNumber)
    viewModel.getStockIssuanceInfo(corpNumber)

    Column {
        Text(text = corporationInfoState.corporationInfo.toString())
        Text(text = stockPriceInfoState.stockPriceInfo.toString())
        Text(text = stockIssuanceInfoState.stockIssuanceInfo.toString())
    }
}