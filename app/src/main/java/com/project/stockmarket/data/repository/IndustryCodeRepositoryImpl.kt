package com.project.stockmarket.data.repository

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.data.dao.IndustryCodeDao
import com.project.stockmarket.data.model.IndustryCodeEntity
import com.project.stockmarket.data.safeFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IndustryCodeRepositoryImpl @Inject constructor(
    private val industryCodeDao: IndustryCodeDao
) {
    suspend fun insertIndustryCode(industryCode: IndustryCodeEntity) {
        industryCodeDao.insertIndustryCode(industryCode)
    }

    suspend fun getIndustryClassificationByIndustryCode(industryCode: String): Flow<NetworkResult<String>> = safeFlow {
        industryCodeDao.getIndustryClassificationByIndustryCode(industryCode)
    }
}