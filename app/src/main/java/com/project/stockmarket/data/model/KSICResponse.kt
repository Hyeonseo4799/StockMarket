package com.project.stockmarket.data.model

import com.project.stockmarket.data.model.KSICResponse.Data
import com.project.stockmarket.domain.model.KSIC

data class KSICResponse(
    val currentCount: Int,
    val `data`: List<Data>,
    val matchCount: Int,
    val page: Int,
    val perPage: Int,
    val totalCount: Int
) {
    data class Data(
        val 데이터기준일: String,
        val 산업분류명칭: String,
        val 산업분류코드: String,
        val 순번: Int
    )
}

fun Data.toKSIC(): KSIC {
    return KSIC(
        baseDate = 데이터기준일,
        industryClassification = 산업분류명칭,
        industryCode = 산업분류코드
    )
}

