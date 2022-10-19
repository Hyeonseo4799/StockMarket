package com.project.stockmarket.di

import com.project.stockmarket.data.repository.IndustryCodeRepositoryImpl
import com.project.stockmarket.data.repository.StockInfoRepositoryImpl
import com.project.stockmarket.data.repository.datasource.IndustryCodeDataSource
import com.project.stockmarket.data.repository.datasource.StockInfoDataSource
import com.project.stockmarket.domain.repository.IndustryCodeRepository
import com.project.stockmarket.domain.repository.StockInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideStockInfoRepository(stockInfoDataSource: StockInfoDataSource): StockInfoRepository {
        return StockInfoRepositoryImpl(stockInfoDataSource)
    }

    @Provides
    @Singleton
    fun provideIndustryCodeRepository(
        stockIndustryCodeDataSource: IndustryCodeDataSource
    ): IndustryCodeRepository {
        return IndustryCodeRepositoryImpl(stockIndustryCodeDataSource)
    }
}