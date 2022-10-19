package com.project.stockmarket.domain.usecase

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.KSIC
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetKSICUseCase @Inject constructor(
    private val repository: StockInfoRepository
) {
    operator fun invoke(): Flow<NetworkResult<List<KSIC>>> {
        return repository.getKSIC()
    }
}