package com.project.stockmarket.domain.repository

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.CorporationInfo
import com.project.stockmarket.domain.model.KrxListedInfo
import com.project.stockmarket.domain.model.StockIssuanceInfo
import com.project.stockmarket.domain.model.StockPriceInfo
import kotlinx.coroutines.flow.Flow

interface StockInfoRepository {
    suspend fun getStockPriceInfo(basDt: String, itmsNm: String): Flow<NetworkResult<List<StockPriceInfo>>>
    suspend fun getCorporationInfo(basDt: String, crno: String): Flow<NetworkResult<List<CorporationInfo>>>
    suspend fun getKrxListedInfo(basDt: String, likeItmsNm: String): Flow<NetworkResult<List<KrxListedInfo>>>
    suspend fun getStockIssuanceInfo(basDt: String, crno: String): Flow<NetworkResult<List<StockIssuanceInfo>>>
}