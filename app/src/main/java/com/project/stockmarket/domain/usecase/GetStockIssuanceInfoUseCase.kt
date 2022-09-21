package com.project.stockmarket.domain.usecase

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.StockIssuanceInfo
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStockIssuanceInfoUseCase @Inject constructor(
    private val repository: StockInfoRepository
) {
    operator fun invoke(basDt: String, crno: String): Flow<NetworkResult<List<StockIssuanceInfo>>> = flow {
        repository.getStockIssuanceInfo(basDt, crno).collect {
            emit(it)
        }
    }
}