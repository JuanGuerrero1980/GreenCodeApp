package com.jg.greencode.domain.usecase

import com.jg.greencode.domain.model.Symbol
import com.jg.greencode.domain.repository.GreenCodeRepository

class GetSymbolsUseCase(
    private val repository: GreenCodeRepository
) {
    suspend operator fun invoke(): List<Symbol> {
        return repository.getAvailableSymbols()
    }
}