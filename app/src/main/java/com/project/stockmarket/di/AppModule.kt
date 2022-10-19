package com.project.stockmarket.di

import android.content.Context
import androidx.room.Room
import com.project.stockmarket.Constants
import com.project.stockmarket.data.api.StockInfoApi
import com.project.stockmarket.data.dao.IndustryCodeDao
import com.project.stockmarket.data.repository.StockInfoRepositoryImpl
import com.project.stockmarket.database.IndustryCodeDatabase
import com.project.stockmarket.domain.repository.StockInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
            .baseUrl(Constants.BASE_URL)
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

    @Provides
    @Singleton
    fun provideIndustryCodeDatabase(
        @ApplicationContext context: Context
    ): IndustryCodeDatabase = Room.databaseBuilder(
        context, IndustryCodeDatabase::class.java, "industry_code_db"
    ).build()

    @Provides
    @Singleton
    fun provideIndustryCodeDao(
        industryCodeDatabase: IndustryCodeDatabase
    ): IndustryCodeDao = industryCodeDatabase.industryCodeDao()
}