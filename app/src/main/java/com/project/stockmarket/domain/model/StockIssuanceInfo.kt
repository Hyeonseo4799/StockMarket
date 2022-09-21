package com.project.stockmarket.domain.model

data class StockIssuanceInfo(
    val preferredStockOutstanding: String, // 우선주총발행수
    val commonStockOutStanding: String     // 보통주총발행수
)
