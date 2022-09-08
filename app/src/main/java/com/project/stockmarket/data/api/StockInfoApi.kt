package com.project.stockmarket.data.api

import com.project.stockmarket.data.model.CorporationInfoResponse
import com.project.stockmarket.data.model.KrxListedInfoResponse
import com.project.stockmarket.data.model.StockPriceInfoResponse
import retrofit2.http.GET

interface StockInfoApi {
    @GET("/GetStockSecuritiesInfoService/getStockPriceInfo")
    suspend fun getStockPriceInfo(): StockPriceInfoResponse

    @GET("/GetCorpBasicInfoService/getCorpOutline")
    suspend fun getCorporationInfo(): CorporationInfoResponse

    @GET("/GetKrxListedInfoService/getItemInfo")
    suspend fun getKrxListedInfo(): List<KrxListedInfoResponse>
}