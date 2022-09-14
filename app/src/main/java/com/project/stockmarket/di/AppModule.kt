package com.project.stockmarket.di

import com.project.stockmarket.data.api.StockInfoApi
import com.project.stockmarket.data.repository.StockInfoRepositoryImpl
import com.project.stockmarket.domain.repository.StockInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockInfoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStockInfoRepository(api: StockInfoApi): StockInfoRepository {
        return StockInfoRepositoryImpl(api)
    }
}