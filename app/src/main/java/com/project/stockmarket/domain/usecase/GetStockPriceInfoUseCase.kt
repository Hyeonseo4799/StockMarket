package com.project.stockmarket.domain.usecase

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.StockPriceInfo
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStockPriceInfoUseCase @Inject constructor(
    private val repository: StockInfoRepository
) {
    operator fun invoke(basDt: String, itmsNm: String): Flow<NetworkResult<List<StockPriceInfo>>> = flow {
        repository.getStockPriceInfo(basDt, itmsNm).collect {
            emit(it)
        }
    }
}