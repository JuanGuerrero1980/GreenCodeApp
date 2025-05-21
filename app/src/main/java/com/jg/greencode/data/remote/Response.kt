package com.jg.greencode.data.remote

data class CurrencyResponse(
    val result: Double,
    val info: Info
)

data class Info(
    val timestamp: Long?,
    val quote: Double?,
)

data class SymbolsResponse(
    val currencies: Map<String, String>
)