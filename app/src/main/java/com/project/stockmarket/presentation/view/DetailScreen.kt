package com.project.stockmarket.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

    viewModel.getStockPriceInfo(isinCode)
    viewModel.getCorporationInfo(corpNumber)
    viewModel.getStockIssuanceInfo(corpNumber)
    viewModel.getKoreaStandardIndustryCode()

    DetailView(state = stockPriceInfoState, navController = navController)
}

@Composable
fun DetailView(state: DataState<StockPriceInfo>, navController: NavController) {
    if (state.data.isNotEmpty()) {
        TopBar(text = state.data[0].stockName, content = { Content() }) {
            navController.popBackStack()
        }
    }
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(start = 20.dp, end = 20.dp, top = 26.dp, bottom = 26.dp)
                .shadow(5.dp, RoundedCornerShape(8.dp))
                .background(MaterialTheme.colors.background, RoundedCornerShape(8.dp))
        ) {

        }
    }
}