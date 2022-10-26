package com.project.stockmarket.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.stockmarket.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    if (state.data.stockPriceInfo != null)
                        TopBarContent(text = state.data.stockPriceInfo!!.stockName, navController = navController)
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) { }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                ) {
                    if (state.isLoading) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }

                    if (state.data.corporationInfo != null &&
                        state.data.stockPriceInfo != null &&
                        state.data.stockIssuanceInfo != null &&
                        state.data.industryClassification != null
                    ) {
                        val corporationInfo = state.data.corporationInfo
                        Contents(
                            corporationInfoState = state.data.corporationInfo!!,
                            stockPriceInfoState = state.data.stockPriceInfo!!,
                            stockIssuanceInfoState = state.data.stockIssuanceInfo!!,
                            industryClassification = state.data.industryClassification!!
                        ) {
                            val url = corporationInfo!!.homepageUrl
                            val urlHandler = LocalUriHandler.current
                            urlHandler.openUri("http://${url}")
                        }
                    }
                }
            }
        )
    }
}