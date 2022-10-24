package com.project.stockmarket.data.repository.datasource

import com.project.stockmarket.data.dao.IndustryCodeDao
import com.project.stockmarket.domain.model.KSIC
import com.project.stockmarket.domain.model.toKSICEntity
import javax.inject.Inject

class IndustryCodeDataSourceImpl @Inject constructor(
    private val industryCodeDao: IndustryCodeDao
): IndustryCodeDataSource {
    override suspend fun insertIndustryCode(ksic: KSIC) {
        industryCodeDao.insertIndustryCode(ksic.toKSICEntity())
    }

    override suspend fun getIndustryClassificationByKSIC(industryCode: String): String {
        return industryCodeDao.getIndustryClassificationByKSIC(industryCode)
    }
}