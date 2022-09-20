package com.project.stockmarket.domain.repository

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.CorporationInfo
import com.project.stockmarket.domain.model.KrxListedInfo
import com.project.stockmarket.domain.model.StockPriceInfo
import kotlinx.coroutines.flow.Flow

interface StockInfoRepository {
    suspend fun getStockPriceInfo(): Flow<NetworkResult<StockPriceInfo>>
    suspend fun getCorporationInfo(basDt: String, crno: String): Flow<NetworkResult<List<CorporationInfo>>>
    suspend fun getKrxListedInfo(basDt: String, likeItmsNm: String): Flow<NetworkResult<List<KrxListedInfo>>>
}