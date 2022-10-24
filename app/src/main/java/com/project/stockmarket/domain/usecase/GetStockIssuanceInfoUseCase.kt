package com.project.stockmarket.domain.usecase

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.StockIssuanceInfo
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStockIssuanceInfoUseCase @Inject constructor(
    private val repository: StockInfoRepository
) {
    operator fun invoke(baseDate: String, corpNumber: String): Flow<NetworkResult<List<StockIssuanceInfo>>> {
        return repository.getStockIssuanceInfo(baseDate, corpNumber)
    }
}