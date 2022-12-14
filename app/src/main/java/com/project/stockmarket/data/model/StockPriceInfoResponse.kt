package com.project.stockmarket.data.model

import com.project.stockmarket.domain.model.StockPriceInfo
import com.project.stockmarket.data.model.StockPriceInfoResponse.Response.Body.Items.Item

data class StockPriceInfoResponse(
    val response: Response
) {
    data class Response(
        val body: Body,
        val header: Header
    ) {
        data class Body(
            val items: Items,
            val numOfRows: Int,
            val pageNo: Int,
            val totalCount: Int
        ) {
            data class Items(
                val item: List<Item>
            ) {
                data class Item(
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
            }
        }

        data class Header(
            val resultCode: String,
            val resultMsg: String
        )
    }
}

fun Item.toStockPriceInfo(): StockPriceInfo {
    return StockPriceInfo(
        baseDate = basDt,
        closingPrice = clpr,
        priceChangeRate = fltRt,
        highPrice = hipr,
        stockName = itmsNm,
        lowPrice = lopr,
        openingPrice = mkp,
        marketCategory = mrktCtg,
        marketCap = mrktTotAmt,
        ticker = srtnCd,
        tradingValue = trPrc,
        tradingVolume = trqu,
        priceChange = vs
    )
}