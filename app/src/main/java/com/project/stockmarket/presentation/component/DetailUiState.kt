package com.project.stockmarket.presentation.component

import com.project.stockmarket.domain.model.*

data class DetailUiState(
    val isLoading: Boolean = false,
    val data: DetailData = DetailData(),
    val error: String = ""
)

data class DetailData(
    val corporationInfo: CorporationInfo? = null,
    val stockPriceInfo: StockPriceInfo? = null,
    val stockIssuanceInfo: StockIssuanceInfo? = null,
    val industryClassification: String? = null,
)