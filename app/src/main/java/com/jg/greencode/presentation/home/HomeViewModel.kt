package com.jg.greencode.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jg.greencode.domain.usecase.GetSymbolsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.jg.greencode.domain.model.Conversion
import com.jg.greencode.domain.usecase.DoConversionUseCase
import com.jg.greencode.domain.usecase.GetHistoryConversionUseCase
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSymbolsUseCase: GetSymbolsUseCase,
    private val doConversionUseCase: DoConversionUseCase,
    private val getConversionHistoryUseCase: GetHistoryConversionUseCase,
): ViewModel() {



    private val _conversion = MutableStateFlow<List<Conversion>>(emptyList())
    val conversion: StateFlow<List<Conversion>> = _conversion.asStateFlow()

    var amount1 by mutableStateOf("")
        private set
    var amount2 by mutableStateOf("")
        private set
    var currency1 by mutableStateOf("USD")
        private set
    var currency2 by mutableStateOf("ARS")
        private set
    var currencies by mutableStateOf(emptyMap<String, String>())
        private set
    var loading by mutableStateOf(false)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    init {
        viewModelScope.launch {
            loading = true
            error = null
            try {
                val symbols = getSymbolsUseCase()
                val fetchedCurrencies = mutableMapOf<String, String>()
                symbols.forEach {
                    fetchedCurrencies[it.code] = it.description
                }
                currencies = fetchedCurrencies
            } catch (e: Exception) {
                error =  "Failed to load currencies. Please try again later."
            }
            loading = false
            fetchConversionHistory()
        }
    }


    fun updateAmount1(value: String) {
        amount1 = value
    }

    fun updateAmount2(value: String) {
        amount2 = value

    }

    fun updateCurrency1(newCurrency: String) {
        currency1 = newCurrency
    }

    fun fetchExchangeRates() = viewModelScope.launch(Dispatchers.IO) {
        if(amount1.isEmpty()) return@launch
        loading = true
        error = null
        try {
            val conversion = doConversionUseCase(currency1, currency2, amount1.toDouble())
            updateAmount2(conversion.result.toString())
        } catch (e: Exception) {
            error = "Failed to convert currency. Please try again later."
        }
        loading = false
    }

    fun fetchConversionHistory() = viewModelScope.launch(Dispatchers.IO) {

        try {
            getConversionHistoryUseCase().collect { conversion ->
                _conversion.value = conversion
            }
        } catch (e: Exception) {
            error = "Failed to fetch conversion history. Please try again later."
        }
    }

    fun updateCurrency2(newCurrency: String) {
        currency2 = newCurrency
        if (amount2.isNotEmpty()) {
            updateAmount1(amount2)
        }
    }

    fun swapCurrencies() {
        val tempCurrency = currency1
        currency1 = currency2
        currency2 = tempCurrency

        val tempAmount = amount1
        amount1 = amount2
        amount2 = tempAmount

    }
}