package com.project.stockmarket.data.repository.datasource

import com.project.stockmarket.data.model.KSICEntity

interface IndustryCodeDataSource {
    suspend fun insertIndustryCode(ksicEntity: KSICEntity)
    suspend fun getIndustryClassificationByKSIC(industryCode: String): String
}