package com.project.stockmarket.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.stockmarket.data.dao.IndustryCodeDao
import com.project.stockmarket.data.model.KSICEntity

@Database(entities = [KSICEntity::class], version = 1, exportSchema = false)
abstract class KSICDatabase: RoomDatabase() {
    abstract fun industryCodeDao(): IndustryCodeDao
}