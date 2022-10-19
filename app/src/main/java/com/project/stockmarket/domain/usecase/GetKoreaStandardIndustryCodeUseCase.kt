package com.project.stockmarket.domain.usecase

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.IndustryCode
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetKoreaStandardIndustryCodeUseCase @Inject constructor(
    private val repository: StockInfoRepository
) {
    operator fun invoke(): Flow<NetworkResult<List<IndustryCode>>> {
        return repository.getKoreaStandardIndustryCode()
    }
}