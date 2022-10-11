package com.project.stockmarket.presentation.view

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    val industryClassificationState = viewModel.industryClassificationState.value

    viewModel.getStockPriceInfo(isinCode)
    viewModel.getCorporationInfo(corpNumber)
    viewModel.getStockIssuanceInfo(corpNumber)

    Log.d(TAG, isinCode)

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

            if (corporationInfoState.data.isNotEmpty()) {
                Log.d(TAG, corporationInfoState.data[0].IndustryCode)
                viewModel.getIndustryClassificationByIndustryCode(corporationInfoState.data[0].IndustryCode)
                Log.d(TAG, industryClassificationState.toString())
            }


            if (stockPriceInfoState.data.isNotEmpty() && industryClassificationState != null) {
                CardView(
                    stockPriceInfo = stockPriceInfoState.data[0],
                    industryClassification = industryClassificationState
                )
            }
        }
    )
}
//        val fdas = industryCodeList.getBundle("fdsa") as DataState<KoreaStandardIndustryCode>
//        val industryCode = getIndustryClassification(industryCodeState, corporationInfoState)


//fun getIndustryClassification(
//    industryCodeList: ArrayList<String>,
//    corporationInfoState: DataState<CorporationInfo>
//): KoreaStandardIndustryCode {
//    val industryCode = industryCodeList.filter {
//        it == corporationInfoState.data[0].IndustryCode
//    }[0].
//    return industryCode[0]
//        .filter {
//        it. == corporationInfoState.data[0].IndustryCode
//    }
//    val industryCode = industryCodeState.data.let {
//        if (corporationInfoState.data.isNotEmpty()) {
//            it.filter { koreaStandardIndustryCode ->
//
//            }
//        } else null
//    }
//    return if (industryCode != null) industryCode[0] else null
//}
