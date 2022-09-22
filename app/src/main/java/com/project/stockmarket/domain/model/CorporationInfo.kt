package com.project.stockmarket.domain.model

data class CorporationInfo(
    val auditReportOpinion: String, // 감사보고서의견내용
    val corpEngName: String,        // 법인영문명
    val corpName: String,           // 법인명
    val address: String,            // 기업기본주소
    val establishment: String,      // 기업설립일자
    val homepageUrl: String,        // 기업홈페이지URL
    val ownerName: String,          // 기업대표자성명
    val telNumber: String,          // 기업전화번호
    val IndustryCode: String,       // 표준산업분류명
)