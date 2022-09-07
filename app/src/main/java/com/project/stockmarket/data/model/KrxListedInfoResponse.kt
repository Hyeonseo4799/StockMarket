package com.project.stockmarket.data.model

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
                    val basDt: String,      // 기준일
                    val corpNm: String,     // 법인 명칭
                    val crno: String,       // 법인등록번호
                    val isinCd: String,     // 국제 채권 식별 번호
                    val itmsNm: String,     // 주식회사명
                    val mrktCtg: String,    // 시장 구분 (코스피/코스닥/코넥스)
                    val srtnCd: String      // 종목 코드
                )
            }
        }

        data class Header(
            val resultCode: String,
            val resultMsg: String
        )
    }
}