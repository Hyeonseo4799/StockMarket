package com.project.stockmarket.data.api

import com.project.stockmarket.common.Constants
import com.project.stockmarket.data.model.CorporationInfoResponse
import com.project.stockmarket.data.model.KrxListedInfoResponse
import com.project.stockmarket.data.model.StockPriceInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StockInfoApi {
    @GET("/GetStockSecuritiesInfoService/getStockPriceInfo")
    suspend fun getStockPriceInfo(): StockPriceInfoResponse

    @GET("/GetCorpBasicInfoService/getCorpOutline")
    suspend fun getCorporationInfo(): CorporationInfoResponse

    @GET("1160100/service/GetKrxListedInfoService/getItemInfo")
    suspend fun getKrxListedInfo(
        @Query("serviceKey", encoded = true) serviceKey: String = Constants.serviceKey,
        @Query("resultType") resultType: String = "json",
        @Query("basDt") basDt: String,
        @Query("likeItmsNm") likeItmsNm: String
    ): KrxListedInfoResponse
}