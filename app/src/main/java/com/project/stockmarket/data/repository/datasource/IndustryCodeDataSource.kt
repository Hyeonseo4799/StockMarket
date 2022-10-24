package com.project.stockmarket.data.repository.datasource

import com.project.stockmarket.domain.model.KSIC

interface IndustryCodeDataSource {
    suspend fun insertIndustryCode(ksic: KSIC)
    suspend fun getIndustryClassificationByKSIC(industryCode: String): String
}