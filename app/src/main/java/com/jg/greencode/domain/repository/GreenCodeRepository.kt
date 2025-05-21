package com.jg.greencode.domain.repository

import com.jg.greencode.domain.model.Conversion
import com.jg.greencode.domain.model.Symbol
import kotlinx.coroutines.flow.Flow

interface GreenCodeRepository {

    suspend fun convert(from: String, to: String, amount: Double): Conversion

    fun getHistory(): Flow<List<Conversion>>

    suspend fun getConversion(id: Int): Conversion

    suspend fun getAvailableSymbols(): List<Symbol>
}