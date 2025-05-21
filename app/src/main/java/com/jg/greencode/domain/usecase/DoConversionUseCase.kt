package com.jg.greencode.domain.usecase

import com.jg.greencode.domain.model.Conversion
import com.jg.greencode.domain.repository.GreenCodeRepository


class DoConversionUseCase(
    private val repository: GreenCodeRepository
) {
    suspend operator fun invoke(from: String, to: String, amount: Double): Conversion {
        return repository.convert(from = from, to = to, amount = amount)
    }
}