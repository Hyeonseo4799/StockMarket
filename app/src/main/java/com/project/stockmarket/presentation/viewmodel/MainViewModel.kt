package com.project.stockmarket.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.project.stockmarket.domain.model.KSIC
import com.project.stockmarket.domain.usecase.DetailUseCases
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.component.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val detailUseCases: DetailUseCases
) : BaseViewModel() {
    private val _state = MutableStateFlow(DetailUiState())
    val state: StateFlow<DetailUiState> = _state

    init {
        getKSIC()
    }

    private fun getKSIC() {
        viewModelScope.launch {
            detailUseCases.getKSIC().collect { result ->
                try {
                    result.data?.let { insertIndustryCode(it) }
                } catch (e: Exception) {
                    _state.value.error = e.localizedMessage ?: "Unknown Error"
                }
            }
        }
    }

    fun insertIndustryCode(ksic: List<KSIC>) {
        viewModelScope.launch {
            ksic.forEach {
                detailUseCases.setIndustryCode(it)
            }
        }
    }
}