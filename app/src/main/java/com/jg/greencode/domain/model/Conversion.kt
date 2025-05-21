package com.jg.greencode.domain.model

data class Conversion (
    val id: Int = 0,
    val fromCurrency: String,
    val toCurrency: String,
    val amount: Double,
    val result: Double,
    val rate: Double,
    val date: Long
)