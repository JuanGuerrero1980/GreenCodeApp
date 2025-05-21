package com.jg.greencode.domain.usecase

import com.jg.greencode.domain.model.Conversion
import com.jg.greencode.domain.repository.GreenCodeRepository

class GetConversionUseCase(
    private val repository: GreenCodeRepository
) {
    suspend operator fun invoke(id: Int): Conversion {
        return repository.getConversion(id = id)
    }
}