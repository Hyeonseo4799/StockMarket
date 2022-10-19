package com.project.stockmarket.data.repository.datasource

import com.project.stockmarket.data.dao.IndustryCodeDao
import com.project.stockmarket.data.model.KSICEntity
import javax.inject.Inject

class IndustryCodeDataSourceImpl @Inject constructor(
    private val industryCodeDao: IndustryCodeDao
): IndustryCodeDataSource {
    override suspend fun insertIndustryCode(ksicEntity: KSICEntity) {
        industryCodeDao.insertIndustryCode(ksicEntity)
    }

    override suspend fun getIndustryClassificationByKSIC(industryCode: String): String {
        return industryCodeDao.getIndustryClassificationByKSIC(industryCode)
    }
}