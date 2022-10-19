package com.project.stockmarket.data.repository.datasource

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.*
import kotlinx.coroutines.flow.Flow

interface StockInfoDataSource {
    fun getStockPriceInfo(basDt: String, itmsNm: String): Flow<NetworkResult<List<StockPriceInfo>>>
    fun getCorporationInfo(basDt: String, crno: String): Flow<NetworkResult<List<CorporationInfo>>>
    fun getKrxListedInfo(basDt: String, likeItmsNm: String): Flow<NetworkResult<List<KrxListedInfo>>>
    fun getStockIssuanceInfo(basDt: String, crno: String): Flow<NetworkResult<List<StockIssuanceInfo>>>
    fun getKSIC(): Flow<NetworkResult<List<KSIC>>>
}