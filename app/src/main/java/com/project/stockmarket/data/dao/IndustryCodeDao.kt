package com.project.stockmarket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.stockmarket.data.model.IndustryCodeEntity

@Dao
interface IndustryCodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIndustryCode(industryCode: IndustryCodeEntity)

    @Query("SELECT industryClassification FROM industry_code WHERE industryCode = :industryCode")
    suspend fun getIndustryClassificationByIndustryCode(industryCode: String): String
}