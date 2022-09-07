package com.project.stockmarket.domain.model

data class StockPriceInfo(
    val baseDate: String,           // 기준일
    val closingPrice: String,       // 종가
    val priceChangeRate: String,    // 전일 대비 등락 비율
    val highPrice: String,          // 고가
    val stockName: String,          // 주식회사명
    val lowPrice: String,           // 저가
    val sharesOutstanding: String,  // 상장 주식수
    val openingPrice: String,       // 시초가
    val marketCategory: String,     // 시장 구분 (코스피/코스닥/코넥스)
    val marketCap: String,          // 시가총액
    val ticker: String,             // 종목 코드
    val tradingValue: String,       // 거래대금
    val tradingVolume: String,      // 거래량
    val priceChange: String         // 전일 대비 등락
)
