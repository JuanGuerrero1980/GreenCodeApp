package com.jg.greencode.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jg.greencode.domain.model.Conversion
import com.jg.greencode.domain.usecase.GetConversionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getConversionUseCase: GetConversionUseCase,
): ViewModel() {

    fun getConversionById(id: Int): StateFlow<Conversion?> {
        val result = MutableStateFlow<Conversion?>(null)
        viewModelScope.launch(Dispatchers.IO) {
            result.value = getConversionUseCase(id)
        }
        return result
    }
}