package com.project.stockmarket.data.model

import com.project.stockmarket.domain.model.CorporationInfo

data class CorporationInfoResponse(
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

fun CorporationInfoResponse.toCorporationInfo(): CorporationInfo {
    return CorporationInfo(
        auditReportOpinion = actnAudpnNm,
        corpEngName = corpEnsnNm,
        corpName = corpNm,
        address = enpBsadr,
        establishment = enpEstbDt,
        homepageUrl = enpHmpgUrl,
        mainBusiness = enpMainBizNm,
        ownerName = enpRprFnm,
        telNumber = enpTlno,
        IndustryCode = sicNm
    )
}