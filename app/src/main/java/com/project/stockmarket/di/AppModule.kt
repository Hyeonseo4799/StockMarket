package com.project.stockmarket.di

import com.project.stockmarket.common.Constants
import com.project.stockmarket.data.api.StockInfoApi
import com.project.stockmarket.data.repository.StockInfoRepositoryImpl
import com.project.stockmarket.domain.repository.StockInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideStockInfoApi(): StockInfoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkhttpClient())
            .build()
            .create(StockInfoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val httpinterceptor = HttpLoggingInterceptor()
        httpinterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(httpinterceptor).build()
    }

    @Provides
    @Singleton
    fun provideStockInfoRepository(api: StockInfoApi): StockInfoRepository {
        return StockInfoRepositoryImpl(api)
    }
}