package com.project.stockmarket.data.model

import com.project.stockmarket.domain.model.KrxListedInfo

data class KrxListedInfoResponse(
    val basDt: String,
    val corpNm: String,
    val crno: String,
    val isinCd: String,
    val itmsNm: String,
    val mrktCtg: String,
    val srtnCd: String
)

fun KrxListedInfoResponse.toKrxListedInfo(): KrxListedInfo {
    return KrxListedInfo(
        corpNumber = crno,
        corpName = corpNm,
        stockName = itmsNm
    )
}