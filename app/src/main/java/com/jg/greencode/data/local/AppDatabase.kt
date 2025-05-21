package com.jg.greencode.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jg.greencode.data.local.dao.ConversionDao
import com.jg.greencode.data.local.entity.ConversionEntity

@Database(
    entities = [ConversionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun conversionDao(): ConversionDao
}