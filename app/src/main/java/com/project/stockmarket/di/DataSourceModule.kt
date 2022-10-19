package com.project.stockmarket.di

import com.project.stockmarket.data.api.StockInfoApi
import com.project.stockmarket.data.dao.IndustryCodeDao
import com.project.stockmarket.data.repository.datasource.IndustryCodeDataSource
import com.project.stockmarket.data.repository.datasource.IndustryCodeDataSourceImpl
import com.project.stockmarket.data.repository.datasource.StockInfoDataSource
import com.project.stockmarket.data.repository.datasource.StockInfoDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideIndustryCodeDataSource(industryCodeDao: IndustryCodeDao): IndustryCodeDataSource {
        return IndustryCodeDataSourceImpl(industryCodeDao)
    }

    @Provides
    @Singleton
    fun provideStockInfoDataSource(api: StockInfoApi): StockInfoDataSource {
        return StockInfoDataSourceImpl(api)
    }
}