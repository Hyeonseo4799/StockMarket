package com.project.stockmarket.domain.repository

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.data.model.KSICEntity
import kotlinx.coroutines.flow.Flow

interface IndustryCodeRepository {
    suspend fun insertIndustryCode(industryCode: KSICEntity)
    fun getIndustryClassificationByKSIC(ksic: String): Flow<NetworkResult<String>>
}