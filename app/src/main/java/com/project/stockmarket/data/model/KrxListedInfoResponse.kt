package com.project.stockmarket.data.model

import com.project.stockmarket.domain.model.KrxListedInfo
import com.project.stockmarket.data.model.KrxListedInfoResponse.Response.Body.Items.Item

data class KrxListedInfoResponse(
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
                    val corpNm: String,
                    val crno: String,
                    val isinCd: String,
                    val itmsNm: String,
                    val mrktCtg: String,
                    val srtnCd: String
                )
            }
        }

        data class Header(
            val resultCode: String,
            val resultMsg: String
        )
    }
}

fun Item.toKrxListedInfo(): KrxListedInfo {
    return KrxListedInfo(
        corpNumber = crno,
        corpName = corpNm,
        stockName = itmsNm,
        isinCode = isinCd
    )
}