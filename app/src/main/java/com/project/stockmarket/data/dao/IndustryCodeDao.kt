package com.project.stockmarket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.stockmarket.data.model.KSICEntity

@Dao
interface IndustryCodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIndustryCode(ksicEntity: KSICEntity)

    @Query("SELECT industryClassification FROM ksic_db WHERE industryCode = :industryCode")
    suspend fun getIndustryClassificationByKSIC(industryCode: String): String
}