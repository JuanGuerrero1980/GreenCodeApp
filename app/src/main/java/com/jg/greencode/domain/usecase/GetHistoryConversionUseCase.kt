package com.jg.greencode.domain.usecase

import com.jg.greencode.domain.model.Conversion
import com.jg.greencode.domain.repository.GreenCodeRepository
import kotlinx.coroutines.flow.Flow

class GetHistoryConversionUseCase(
    private val repository: GreenCodeRepository
) {
    suspend operator fun invoke(): Flow<List<Conversion>> {
        return repository.getHistory()
    }
}