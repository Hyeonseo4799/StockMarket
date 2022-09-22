package com.project.stockmarket.domain.usecase

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.KoreaStandardIndustryCode
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetKoreaStandardIndustryCodeUseCase @Inject constructor(
    private val repository: StockInfoRepository
) {
    operator fun invoke(): Flow<NetworkResult<List<KoreaStandardIndustryCode>>> = flow {
        repository.getKoreaStandardIndustryCode().collect {
            emit(it)
        }
    }
}