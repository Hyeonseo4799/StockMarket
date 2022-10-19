package com.project.stockmarket.data.repository

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.data.repository.datasource.IndustryCodeDataSource
import com.project.stockmarket.data.model.KSICEntity
import com.project.stockmarket.data.safeFlow
import com.project.stockmarket.domain.repository.IndustryCodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IndustryCodeRepositoryImpl @Inject constructor(
    private val industryCodeDataSource: IndustryCodeDataSource
) : IndustryCodeRepository {
    override suspend fun insertIndustryCode(industryCode: KSICEntity) {
        industryCodeDataSource.insertIndustryCode(industryCode)
    }

    override fun getIndustryClassificationByKSIC(
        industryCode: String
    ): Flow<NetworkResult<String>> = safeFlow {
        industryCodeDataSource.getIndustryClassificationByKSIC(industryCode)
    }
}