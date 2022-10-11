package com.project.stockmarket.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.stockmarket.data.dao.IndustryCodeDao
import com.project.stockmarket.data.model.IndustryCodeEntity

@Database(entities = [IndustryCodeEntity::class], version = 1)
abstract class IndustryCodeDatabase: RoomDatabase() {
    abstract fun industryCodeDao(): IndustryCodeDao
}