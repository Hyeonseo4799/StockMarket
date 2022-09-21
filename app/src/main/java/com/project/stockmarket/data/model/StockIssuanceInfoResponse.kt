package com.project.stockmarket.data.model

import com.project.stockmarket.domain.model.StockIssuanceInfo
import com.project.stockmarket.data.model.StockIssuanceInfoResponse.Response.Body.Items.Item

data class StockIssuanceInfoResponse(
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
                    val crno: String,
                    val dpsgCanDt: String,
                    val dpsgRegDt: String,
                    val isinCd: String,
                    val isinCdNm: String,
                    val issuFrmtClsfNm: String,
                    val issuStckCnt: String,
                    val lstgAbolDt: String,
                    val lstgDt: String,
                    val scrsItmsKcd: String,
                    val scrsItmsKcdNm: String,
                    val stckIssuCmpyNm: String,
                    val stckParPrc: String
                )
            }
        }

        data class Header(
            val resultCode: String,
            val resultMsg: String
        )
    }
}

fun Item.toStockIssuanceInfo(): StockIssuanceInfo {
    return StockIssuanceInfo(
        isinCode = isinCd,
        parValue = stckParPrc,
        sharesOutstanding = issuStckCnt
    )
}