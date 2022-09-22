package com.project.stockmarket.data.api

import androidx.compose.ui.unit.Constraints
import com.project.stockmarket.BuildConfig
import com.project.stockmarket.common.Constants
import com.project.stockmarket.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StockInfoApi {
    @GET("1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo")
    suspend fun getStockPriceInfo(
        @Query("serviceKey", encoded = true) serviceKey: String = BuildConfig.API_KEY,
        @Query("resultType") resultType: String = "json",
        @Query("basDt") basDt: String,
        @Query("isinCd") itmsNm: String
    ): StockPriceInfoResponse

    @GET("1160100/service/GetCorpBasicInfoService/getCorpOutline")
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

    @GET("1160100/service/GetStocIssuInfoService/getStocIssuStat")
    suspend fun getStockIssuanceInfo(
        @Query("serviceKey", encoded = true) serviceKey: String = BuildConfig.API_KEY,
        @Query("resultType") resultType: String = "json",
        @Query("basDt") basDt: String,
        @Query("crno") crno: String
    ): StockIssuanceInfoResponse

    @GET
    suspend fun getKoreaStandardIndustryCode(
        @Url url: String = Constants.url,
        @Query("serviceKey", encoded = true) serviceKey: String = BuildConfig.API_KEY,
        @Query("page") page: String = "1",
        @Query("perPage") perPage: String = "2022"
    ): KoreaStandardIndustryCodeResponse
}