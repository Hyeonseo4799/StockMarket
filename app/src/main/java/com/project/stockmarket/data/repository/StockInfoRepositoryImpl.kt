package com.project.stockmarket.data.repository

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.data.api.StockInfoApi
import com.project.stockmarket.data.model.toCorporationInfo
import com.project.stockmarket.data.model.toKrxListedInfo
import com.project.stockmarket.data.model.toStockPriceInfo
import com.project.stockmarket.data.safeFlow
import com.project.stockmarket.domain.model.CorporationInfo
import com.project.stockmarket.domain.model.KrxListedInfo
import com.project.stockmarket.domain.model.StockPriceInfo
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockInfoRepositoryImpl @Inject constructor(
    private val api: StockInfoApi
) : StockInfoRepository {
    override suspend fun getStockPriceInfo(): Flow<NetworkResult<StockPriceInfo>> = safeFlow {
        api.getStockPriceInfo().toStockPriceInfo()
    }

    override suspend fun getCorporationInfo(): Flow<NetworkResult<CorporationInfo>> = safeFlow {
        api.getCorporationInfo().toCorporationInfo()
    }

    override suspend fun getKrxListedInfo(): Flow<NetworkResult<List<KrxListedInfo>>> = safeFlow {
        api.getKrxListedInfo().map { it.toKrxListedInfo() }
    }
}