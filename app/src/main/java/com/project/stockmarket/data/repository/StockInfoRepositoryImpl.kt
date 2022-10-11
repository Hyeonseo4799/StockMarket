package com.project.stockmarket.data.repository

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.data.api.StockInfoApi
import com.project.stockmarket.data.model.*
import com.project.stockmarket.data.safeFlow
import com.project.stockmarket.domain.model.*
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockInfoRepositoryImpl @Inject constructor(
    private val api: StockInfoApi
) : StockInfoRepository {
    override suspend fun getStockPriceInfo(basDt: String, itmsNm: String): Flow<NetworkResult<List<StockPriceInfo>>> = safeFlow {
        api.getStockPriceInfo(basDt = basDt, itmsNm = itmsNm).response.body.items.item.map { it.toStockPriceInfo() }
    }

    override suspend fun getCorporationInfo(basDt: String, crno: String): Flow<NetworkResult<List<CorporationInfo>>> = safeFlow {
        api.getCorporationInfo(basDt = basDt, crno = crno).response.body.items.item.map { it.toCorporationInfo() }
    }

    override suspend fun getKrxListedInfo(basDt: String, likeItmsNm: String): Flow<NetworkResult<List<KrxListedInfo>>> = safeFlow {
        api.getKrxListedInfo(basDt = basDt, likeItmsNm = likeItmsNm).response.body.items.item.map { it.toKrxListedInfo() }
    }

    override suspend fun getStockIssuanceInfo(basDt: String, crno: String): Flow<NetworkResult<List<StockIssuanceInfo>>> = safeFlow {
        api.getStockIssuanceInfo(basDt = basDt, crno = crno).response.body.items.item.map { it.toStockIssuanceInfo() }
    }

    override suspend fun getKoreaStandardIndustryCode(): Flow<NetworkResult<List<IndustryCode>>> = safeFlow {
        api.getKoreaStandardIndustryCode().data.map { it.toKoreaStandardIndustryCode() }
    }
}