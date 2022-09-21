package com.project.stockmarket.data.api

import com.project.stockmarket.BuildConfig
import com.project.stockmarket.data.model.CorporationInfoResponse
import com.project.stockmarket.data.model.KrxListedInfoResponse
import com.project.stockmarket.data.model.StockIssuanceInfoResponse
import com.project.stockmarket.data.model.StockPriceInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StockInfoApi {
    @GET("/GetStockSecuritiesInfoService/getStockPriceInfo")
    suspend fun getStockPriceInfo(
        @Query("serviceKey", encoded = true) serviceKey: String = BuildConfig.API_KEY,
        @Query("resultType") resultType: String = "json",
        @Query("basDt") basDt: String,
        @Query("itmsNm") itmsNm: String
    ): StockPriceInfoResponse

    @GET("1160100/service/GetCorpBasicInfoService")
    suspend fun getCorporationInfo(
        @Query("serviceKey", encoded = true) serviceKey: String = BuildConfig.API_KEY,
        @Query("resultType") resultType: String = "json",
        @Query("basDt") basDt: String,
        @Query("crno") crno: String
    ): CorporationInfoResponse

    @GET("1160100/service/GetKrxListedInfoService/getItemInfo")
    suspend fun getKrxListedInfo(
        @Query("serviceKey", encoded = true) serviceKey: String = BuildConfig.API_KEY,
        @Query("resultType") resultType: String = "json",
        @Query("basDt") basDt: String,
        @Query("likeItmsNm") likeItmsNm: String
    ): KrxListedInfoResponse

    @GET("1160100/service/GetStocIssuInfoService/getItemBasiInfo")
    suspend fun getStockIssuanceInfo(
        @Query("serviceKey", encoded = true) serviceKey: String = BuildConfig.API_KEY,
        @Query("resultType") resultType: String = "json",
        @Query("basDt") basDt: String,
        @Query("crno") crno: String
    ): StockIssuanceInfoResponse
}