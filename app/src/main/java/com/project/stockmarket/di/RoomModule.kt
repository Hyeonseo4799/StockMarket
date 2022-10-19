package com.project.stockmarket.di

import android.content.Context
import androidx.room.Room
import com.project.stockmarket.Constants
import com.project.stockmarket.data.dao.IndustryCodeDao
import com.project.stockmarket.data.database.KSICDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideIndustryCodeDatabase(
        @ApplicationContext context: Context
    ): KSICDatabase = Room.databaseBuilder(
        context, KSICDatabase::class.java, Constants.DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideIndustryCodeDao(KSICDatabase: KSICDatabase): IndustryCodeDao {
        return KSICDatabase.industryCodeDao()
    }
}