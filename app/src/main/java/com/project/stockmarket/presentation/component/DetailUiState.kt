package com.project.stockmarket.presentation.component

import com.project.stockmarket.domain.model.CorporationInfo
import com.project.stockmarket.domain.model.KSIC
import com.project.stockmarket.domain.model.StockIssuanceInfo
import com.project.stockmarket.domain.model.StockPriceInfo

data class DetailUiState(
    val isLoading: Boolean = false,
    val data: DetailData = DetailData(),
    var error: String = ""
)

data class DetailData(
    val corporationInfo: CorporationInfo? = null,
    val stockPriceInfo: StockPriceInfo? = null,
    val stockIssuanceInfo: StockIssuanceInfo? = null,
    val industryClassification: String? = null,
    val industryCode: String? = null,
    val ksic: List<KSIC> = emptyList(),
)