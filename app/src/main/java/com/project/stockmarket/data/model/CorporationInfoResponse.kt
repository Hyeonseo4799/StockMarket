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
                    val actnAudpnNm: String,        // 회계감사인명
                    val audtRptOpnnCtt: String,     // 감사보고서 의견내용
                    val basDt: String,              // 기준일
                    val bzno: String,               // 사업자등록번호
                    val corpDcd: String,            // 법인구분코드
                    val corpDcdNm: String,          // 법인구분코드명
                    val corpEnsnNm: String,         // 법인영문명
                    val corpNm: String,             // 법인명
                    val corpRegMrktDcd: String,     // 법인등록시장구분코드
                    val corpRegMrktDcdNm: String,   // 법인등록시장구분코드명
                    val crno: String,               // 법인등록번호
                    val empeAvgCnwkTermCtt: String, // 종업원평균근속기간내용
                    val enpBsadr: String,           // 기업기본주소
                    val enpDtadr: String,           // 기업상세주소
                    val enpEmpeCnt: String,         // 기업종업원수
                    val enpEstbDt: String,          // 기업설립일자
                    val enpFxno: String,            // 기업팩스번호
                    val enpHmpgUrl: String,         // 기업홈페이지URL
                    val enpKosdaqLstgAbolDt: String,// 기업코스닥상장폐지일자
                    val enpKosdaqLstgDt: String,    // 기업코스닥상장일자
                    val enpKrxLstgAbolDt: String,   // 기업KONEX상장폐지일자
                    val enpKrxLstgDt: String,       // 기업KONEX상장일자
                    val enpMainBizNm: String,       // 기업주요사업명
                    val enpMntrBnkNm: String,       // 기업주거래은행명
                    val enpOzpno: String,           // 기업구우편번호
                    val enpPbanCmpyNm: String,      // 기업공시회사명
                    val enpPn1AvgSlryAmt: String,   // 기업1인평균급여금액
                    val enpRprFnm: String,          // 기업대표자성명
                    val enpStacMm: String,          // 기업결산월
                    val enpTlno: String,            // 기업전화번호
                    val enpXchgLstgAbolDt: String,  // 기업거래소상장폐지일자
                    val enpXchgLstgDt: String,      // 기업거래소상장일자
                    val fssCorpChgDtm: String,      // 금융감독원법인변경일시
                    val fssCorpUnqNo: String,       // 금융감독원법인고유번호
                    val sicNm: String,              // 표준산업분류명
                    val smenpYn: String             // 중소기업여부
                )
            }
        }

        data class Header(
            val resultCode: String,
            val resultMsg: String
        )
    }
}