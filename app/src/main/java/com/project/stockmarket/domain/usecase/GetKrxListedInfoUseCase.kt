package com.project.stockmarket.domain.usecase

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.KrxListedInfo
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetKrxListedInfoUseCase @Inject constructor(
    private val repository: StockInfoRepository
) {
    operator fun invoke(basDt: String, likeItmsNm: String): Flow<NetworkResult<List<KrxListedInfo>>> {
        return repository.getKrxListedInfo(basDt, likeItmsNm)
    }
}