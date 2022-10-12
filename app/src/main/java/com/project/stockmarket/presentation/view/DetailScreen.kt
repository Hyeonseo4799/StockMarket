package com.project.stockmarket.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
    val corporationInfoState = viewModel.corporationInfoState.value
    val stockPriceInfoState = viewModel.stockPriceInfoState.value
    val stockIssuanceInfoState = viewModel.stockIssuanceInfoState.value
    val industryClassificationState = viewModel.industryClassificationState.value

    viewModel.getStockPriceInfo(isinCode)
    viewModel.getCorporationInfo(corpNumber)
    viewModel.getStockIssuanceInfo(corpNumber)

    if (corporationInfoState.data.isNotEmpty())
        viewModel.getIndustryClassificationByIndustryCode(corporationInfoState.data[0].IndustryCode)

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.background
            ) {
                if (stockPriceInfoState.data.isNotEmpty())
                    TopBarContent(text = stockPriceInfoState.data[0].stockName, navController = navController)
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) { }
            StateView(state = listOf(corporationInfoState, stockPriceInfoState))

            if (stockPriceInfoState.data.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = Modifier.height(26.dp))
                    CardView(
                        stockPriceInfo = stockPriceInfoState.data[0],
                        industryClassification = industryClassificationState
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    if (stockIssuanceInfoState.data.isNotEmpty() && corporationInfoState.data.isNotEmpty()) {
                        Contents(corporationInfoState, stockPriceInfoState, stockIssuanceInfoState) {
                            val url = corporationInfoState.data[0].homepageUrl
                            val urlHandler = LocalUriHandler.current
                            urlHandler.openUri("http://${url}")
                        }
                    }
                }
            }
        }
    )
}