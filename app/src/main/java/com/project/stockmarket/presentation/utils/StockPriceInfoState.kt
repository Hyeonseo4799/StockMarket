package com.project.stockmarket.presentation.utils

import com.project.stockmarket.domain.model.StockPriceInfo

data class StockPriceInfoState(
    val isLoading: Boolean = false,
    val stockPriceInfo: List<StockPriceInfo> = emptyList(),
    val error: String = ""
)