package com.project.stockmarket.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ksic")
data class KSICEntity(
    @PrimaryKey
    val industryCode: String,
    val baseDate: String,
    val industryClassification: String
)