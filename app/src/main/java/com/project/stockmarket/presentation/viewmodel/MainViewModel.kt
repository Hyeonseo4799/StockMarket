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
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val detailUseCases: DetailUseCases
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    fun getKSIC() {
        viewModelScope.launch {
            invokeUseCase(detailUseCases.getKSIC(), _uiState) { result ->
                insertIndustryCode(result.data ?: emptyList())
            }
        }
    }

    private fun insertIndustryCode(ksic: List<KSIC>) {
        viewModelScope.launch {
            ksic.forEach {
                detailUseCases.setIndustryCode(it)
            }
        }
    }
}