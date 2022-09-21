package com.project.stockmarket.domain.model

data class StockIssuanceInfo(
    val isinCode: String,           // 국제채권식별번호
    val parValue: String,           // 액면가
    val sharesOutstanding: String,  // 상장주식수
)
