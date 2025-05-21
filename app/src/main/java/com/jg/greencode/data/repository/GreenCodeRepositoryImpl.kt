package com.jg.greencode.data.repository

import com.jg.greencode.data.local.dao.ConversionDao
import com.jg.greencode.data.local.entity.ConversionEntity
import com.jg.greencode.data.remote.ExchangeApiService
import com.jg.greencode.data.remote.SymbolsResponse
import com.jg.greencode.domain.model.Conversion
import com.jg.greencode.domain.model.Symbol
import com.jg.greencode.domain.repository.GreenCodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GreenCodeRepositoryImpl @Inject constructor(
    private val exchangeApiService: ExchangeApiService,
    private val conversionDao: ConversionDao,
): GreenCodeRepository {

    override suspend fun convert(from: String, to: String, amount: Double): Conversion {
        val response = exchangeApiService.convertCurrency(from = from, to = to, amount = amount)
        val entity = ConversionEntity(
            fromCurrency = from,
            toCurrency = to,
            amount = amount,
            result = response.result,
            rate = response.info.quote ?: 0.0,
            date = System.currentTimeMillis()
        )
        conversionDao.insertConversion(entity)
        return entity.toDomain()
    }

    override fun getHistory(): Flow<List<Conversion>> = conversionDao.getAllConversions()
        .map { conversions ->
            conversions.map { conversionEntity ->
                conversionEntity.toDomain()
            }
        }

    override suspend fun getConversion(id: Int) = conversionDao.getConversionById(id).toDomain()

    override suspend fun getAvailableSymbols(): List<Symbol> =
        exchangeApiService.getSymbols()?.toDomain() ?: emptyList()

}

fun ConversionEntity.toDomain(): Conversion {
    return Conversion(
        id = id,
        fromCurrency = fromCurrency,
        toCurrency = toCurrency,
        amount = amount,
        result = result,
        rate = rate,
        date = date
    )
}

fun SymbolsResponse.toDomain(): List<Symbol> {
    return currencies.map { Symbol(code = it.key, description = it.value) }
}
