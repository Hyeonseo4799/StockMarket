package com.project.stockmarket.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "industry_code")
data class IndustryCodeEntity(
    @PrimaryKey
    val industryCode: String,
    val baseDate: String,
    val industryClassification: String
)