package com.project.stockmarket.data.model

import com.project.stockmarket.domain.model.StockPriceInfo

data class StockPriceInfoResponse(
    val basDt: String,
    val clpr: String,
    val fltRt: String,
    val hipr: String,
    val isinCd: String,
    val itmsNm: String,
    val lopr: String,
    val lstgStCnt: String,
    val mkp: String,
    val mrktCtg: String,
    val mrktTotAmt: String,
    val srtnCd: String,
    val trPrc: String,
    val trqu: String,
    val vs: String
)

fun StockPriceInfoResponse.toStockPriceInfo(): StockPriceInfo {
    return StockPriceInfo(
        baseDate = basDt,
        closingPrice = clpr,
        priceChangeRate = fltRt,
        highPrice = hipr,
        stockName = itmsNm,
        lowPrice = lopr,
        sharesOutstanding = lstgStCnt,
        openingPrice = mkp,
        marketCategory = mrktCtg,
        marketCap = mrktTotAmt,
        ticker = srtnCd,
        tradingValue = trPrc,
        tradingVolume = trqu,
        priceChange = vs
    )
}