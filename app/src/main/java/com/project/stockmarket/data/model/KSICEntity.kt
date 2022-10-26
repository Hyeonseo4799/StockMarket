package com.project.stockmarket.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.stockmarket.Constants

@Entity(tableName = Constants.DB_NAME)
data class KSICEntity(
    @PrimaryKey
    val industryCode: String,
    val industryClassification: String
)