package com.project.stockmarket.domain.usecase

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.CorporationInfo
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCorporationInfoUseCase @Inject constructor(
    private val repository: StockInfoRepository
) {
    operator fun invoke(basDt: String, crno: String): Flow<NetworkResult<List<CorporationInfo>>> {
        return repository.getCorporationInfo(basDt, crno)
    }
}
