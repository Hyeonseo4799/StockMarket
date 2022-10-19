package com.project.stockmarket.domain.model

import com.project.stockmarket.data.model.KSICEntity

data class KSIC(
    val baseDate: String,
    val industryClassification: String,
    val industryCode: String
)

fun KSIC.toKSICEntity(): KSICEntity {
    return KSICEntity(
        baseDate = baseDate,
        industryClassification = industryClassification,
        industryCode = industryCode
    )
}