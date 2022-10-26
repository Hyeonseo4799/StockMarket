package com.project.stockmarket.domain.model

import com.project.stockmarket.data.model.KSICEntity

data class KSIC(
    val industryClassification: String,
    val industryCode: String
)

fun KSIC.toKSICEntity(): KSICEntity {
    return KSICEntity(
        industryClassification = industryClassification,
        industryCode = industryCode
    )
}