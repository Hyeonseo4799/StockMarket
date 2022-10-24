package com.project.stockmarket.domain.repository

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.KSIC
import kotlinx.coroutines.flow.Flow

interface IndustryCodeRepository {
    suspend fun insertIndustryCode(ksic: KSIC)
    fun getIndustryClassificationByKSIC(ksic: String): Flow<NetworkResult<String>>
}