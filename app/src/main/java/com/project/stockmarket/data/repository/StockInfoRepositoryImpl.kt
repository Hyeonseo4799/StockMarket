package com.project.stockmarket.data.repository

import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.data.repository.datasource.StockInfoDataSource
import com.project.stockmarket.domain.model.*
import com.project.stockmarket.domain.repository.StockInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockInfoRepositoryImpl @Inject constructor(
    private val stockInfoDataSource: StockInfoDataSource
) : StockInfoRepository {
    override fun getStockPriceInfo(basDt: String, itmsNm: String): Flow<NetworkResult<List<StockPriceInfo>>> {
        return stockInfoDataSource.getStockPriceInfo(basDt, itmsNm)
    }

    override fun getCorporationInfo(basDt: String, crno: String): Flow<NetworkResult<List<CorporationInfo>>> {
        return stockInfoDataSource.getCorporationInfo(basDt, crno)
    }

    override fun getKrxListedInfo(basDt: String, likeItmsNm: String): Flow<NetworkResult<List<KrxListedInfo>>> {
        return stockInfoDataSource.getKrxListedInfo(basDt, likeItmsNm)
    }

    override fun getStockIssuanceInfo(basDt: String, crno: String): Flow<NetworkResult<List<StockIssuanceInfo>>> {
        return stockInfoDataSource.getStockIssuanceInfo(basDt, crno)
    }

    override fun getKSIC(): Flow<NetworkResult<List<KSIC>>> {
        return stockInfoDataSource.getKSIC()
    }
}