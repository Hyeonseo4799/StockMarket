package com.project.stockmarket.presentation.component

import com.project.stockmarket.domain.model.CorporationInfo
import com.project.stockmarket.domain.model.KSIC
import com.project.stockmarket.domain.model.StockIssuanceInfo
import com.project.stockmarket.domain.model.StockPriceInfo

data class DetailUiState(
    val isLoading: Boolean = false,
    val corporationInfo: List<CorporationInfo> = emptyList(),
    val stockPriceInfo: List<StockPriceInfo> = emptyList(),
    val stockIssuanceInfo: List<StockIssuanceInfo> = emptyList(),
    val industryClassification: String? = null,
    val industryCode: String? = null,
    val ksic: List<KSIC> = emptyList(),
    val error: String = ""
)
