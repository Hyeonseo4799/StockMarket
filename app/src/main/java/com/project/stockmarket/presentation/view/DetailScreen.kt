package com.project.stockmarket.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
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
    val state by viewModel.state.collectAsState()

    viewModel.getStockPriceInfo(isinCode)
    viewModel.getCorporationInfo(corpNumber)
    viewModel.getStockIssuanceInfo(corpNumber)

    if (state.corporationInfo.isNotEmpty())
        viewModel.getIndustryClassificationByKSIC(state.corporationInfo[0].IndustryCode)

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.background
            ) {
                if (state.stockPriceInfo.isNotEmpty())
                    TopBarContent(text = state.stockPriceInfo[0].stockName, navController = navController)
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) { }
            StateView(state)

            if (state.stockPriceInfo.isNotEmpty() &&
                state.stockIssuanceInfo.isNotEmpty() &&
                state.corporationInfo.isNotEmpty() &&
                state.industryClassification != null
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                ) {
                    val corporationInfo = state.corporationInfo[0]
                    Contents(
                        corporationInfoState = state.corporationInfo[0],
                        stockPriceInfoState = state.stockPriceInfo[0],
                        stockIssuanceInfoState = state.stockIssuanceInfo[0],
                        industryClassification = state.industryClassification!!
                    ) {
                        val url = corporationInfo.homepageUrl
                        val urlHandler = LocalUriHandler.current
                        urlHandler.openUri("http://${url}")
                    }
                }
            }
        }
    )
}