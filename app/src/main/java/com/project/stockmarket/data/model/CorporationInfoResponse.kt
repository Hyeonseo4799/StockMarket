package com.project.stockmarket.data.model

data class CorporationInfoResponse(
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
                    val actnAudpnNm: String,
                    val audtRptOpnnCtt: String,
                    val basDt: String,
                    val bzno: String,
                    val corpDcd: String,
                    val corpDcdNm: String,
                    val corpEnsnNm: String,
                    val corpNm: String,
                    val corpRegMrktDcd: String,
                    val corpRegMrktDcdNm: String,
                    val crno: String,
                    val empeAvgCnwkTermCtt: String,
                    val enpBsadr: String,
                    val enpDtadr: String,
                    val enpEmpeCnt: String,
                    val enpEstbDt: String,
                    val enpFxno: String,
                    val enpHmpgUrl: String,
                    val enpKosdaqLstgAbolDt: String,
                    val enpKosdaqLstgDt: String,
                    val enpKrxLstgAbolDt: String,
                    val enpKrxLstgDt: String,
                    val enpMainBizNm: String,
                    val enpMntrBnkNm: String,
                    val enpOzpno: String,
                    val enpPbanCmpyNm: String,
                    val enpPn1AvgSlryAmt: String,
                    val enpRprFnm: String,
                    val enpStacMm: String,
                    val enpTlno: String,
                    val enpXchgLstgAbolDt: String,
                    val enpXchgLstgDt: String,
                    val fssCorpChgDtm: String,
                    val fssCorpUnqNo: String,
                    val sicNm: String,
                    val smenpYn: String
                )
            }
        }

        data class Header(
            val resultCode: String,
            val resultMsg: String
        )
    }
}