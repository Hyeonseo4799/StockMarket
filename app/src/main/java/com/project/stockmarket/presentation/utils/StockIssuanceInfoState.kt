package com.project.stockmarket.presentation.utils

import com.project.stockmarket.domain.model.StockIssuanceInfo

data class StockIssuanceInfoState(
    val isLoading: Boolean = false,
    val stockIssuanceInfo: List<StockIssuanceInfo> = emptyList(),
    val error: String = ""
)