package com.jg.greencode.di

import android.content.Context
import androidx.room.Room
import com.jg.greencode.data.local.AppDatabase
import com.jg.greencode.data.local.dao.ConversionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "green_code_db").build()

    @Provides
    fun provideConversionDao(db: AppDatabase): ConversionDao = db.conversionDao()
}