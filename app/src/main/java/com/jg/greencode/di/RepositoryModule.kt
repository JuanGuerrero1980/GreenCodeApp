package com.jg.greencode.di

import com.jg.greencode.data.local.dao.ConversionDao
import com.jg.greencode.data.remote.ExchangeApiService
import com.jg.greencode.data.repository.GreenCodeRepositoryImpl
import com.jg.greencode.domain.repository.GreenCodeRepository
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
    fun provideGreenCodeRepository(
        exchangeApiService: ExchangeApiService,
        conversionDao: ConversionDao
    ): GreenCodeRepository {
        return GreenCodeRepositoryImpl(
            exchangeApiService = exchangeApiService,
            conversionDao = conversionDao
        )
    }
}
