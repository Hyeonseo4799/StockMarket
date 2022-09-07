package com.project.stockmarket.data.model

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
                    val basDt: String,      // 기준일
                    val clpr: String,       // 종가
                    val fltRt: String,      // 전일 대비 등락 비율
                    val hipr: String,       // 고가
                    val isinCd: String,     // 국제 채권 식별 번호
                    val itmsNm: String,     // 주식회사명
                    val lopr: String,       // 저가
                    val lstgStCnt: String,  // 상장 주식수
                    val mkp: String,        // 시초가
                    val mrktCtg: String,    // 시장 구분 (코스피/코스닥/코넥스)
                    val mrktTotAmt: String, // 시가총액
                    val srtnCd: String,     // 종목 코드
                    val trPrc: String,      // 거래대금
                    val trqu: String,       // 거래량
                    val vs: String          // 전일 대비 등락
                )
            }
        }

        data class Header(
            val resultCode: String,
            val resultMsg: String
        )
    }
}