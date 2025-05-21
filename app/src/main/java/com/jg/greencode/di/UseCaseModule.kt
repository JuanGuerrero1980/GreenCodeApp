package com.jg.greencode.di

import com.jg.greencode.domain.repository.GreenCodeRepository
import com.jg.greencode.domain.usecase.DoConversionUseCase
import com.jg.greencode.domain.usecase.GetConversionUseCase
import com.jg.greencode.domain.usecase.GetHistoryConversionUseCase
import com.jg.greencode.domain.usecase.GetSymbolsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetSymbolsUseCase(repository: GreenCodeRepository): GetSymbolsUseCase {
         return GetSymbolsUseCase(repository)
    }

     @Provides
     @Singleton
     fun provideGetAmountsUseCase(repository: GreenCodeRepository): DoConversionUseCase {
         return DoConversionUseCase(repository)
     }

    @Provides
    @Singleton
    fun provideGetHistoryConversionUseCase(repository: GreenCodeRepository): GetHistoryConversionUseCase {
        return GetHistoryConversionUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetConversionUseCase(repository: GreenCodeRepository): GetConversionUseCase {
        return GetConversionUseCase(repository)
    }
}