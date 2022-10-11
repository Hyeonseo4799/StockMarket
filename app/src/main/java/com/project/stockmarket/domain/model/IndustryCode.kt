package com.project.stockmarket.domain.model

import com.project.stockmarket.data.model.IndustryCodeEntity


data class IndustryCode(
    val baseDate: String,
    val industryClassification: String,
    val industryCode: String
)

fun IndustryCode.toIndustryCodeEntity(): IndustryCodeEntity {
    return IndustryCodeEntity(
        baseDate = baseDate,
        industryClassification = industryClassification,
        industryCode = industryCode
    )
}