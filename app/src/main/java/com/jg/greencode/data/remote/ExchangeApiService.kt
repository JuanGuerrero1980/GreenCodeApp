package com.jg.greencode.data.remote

import com.jg.greencode.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeApiService {
    @GET("convert")
    suspend fun convertCurrency(
        @Query("access_key") accessKey: String = BuildConfig.API_KEY,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ): CurrencyResponse

    @GET("list")
    suspend fun getSymbols(
        @Query("access_key") accessKey: String = BuildConfig.API_KEY
    ): SymbolsResponse?
}
