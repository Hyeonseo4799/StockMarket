package com.project.stockmarket.domain.usecase

import android.util.Log
import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.KrxListedInfo
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetKrxListedInfoUseCase @Inject constructor(
    private val repository: StockInfoRepository
) {
    operator fun invoke(basDt: String, likeItmsNm: String): Flow<NetworkResult<List<KrxListedInfo>>> = flow {
        repository.getKrxListedInfo(basDt, likeItmsNm).collect {
            emit(it)
        }
    }
}