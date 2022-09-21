package com.project.stockmarket.domain.model

data class KrxListedInfo(
    val corpNumber: String, // 법인등록번호
    val corpName: String,   // 법인명
    val stockName: String,  // 주식회사명
    val isinCode: String    // 국제채권식별번호
)
