package com.project.stockmarket.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.domain.usecase.DetailUseCases
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.component.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCases: DetailUseCases,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    private val corpNumber = savedStateHandle.get<String>("corpNumber")!!
    private val isinCode = savedStateHandle.get<String>("isinCode")!!

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val deferred = listOf(
                async { getCorporationInfo(corpNumber) },
                async { getStockPriceInfo(isinCode) },
                async { getStockIssuanceInfo(corpNumber) }
            )
            deferred.awaitAll()
            getIndustryClassification(_uiState.value.data.corporationInfo!!.IndustryCode)
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private suspend fun getCorporationInfo(corpNumber: String) {
        invokeUseCase(detailUseCases.getCorporationInfo(getBaseDate(), corpNumber), _uiState) { result ->
            _uiState.update { it.copy(data = _uiState.value.data.copy(corporationInfo = result.data?.get(0))) }
        }
    }

    private suspend fun getStockPriceInfo(isinCode: String) {
        invokeUseCase(detailUseCases.getStockPriceInfo(getBaseDate(), isinCode), _uiState) { result ->
            _uiState.update { it.copy(data = _uiState.value.data.copy(stockPriceInfo = result.data?.get(0))) }
        }
    }

    private suspend fun getStockIssuanceInfo(corpNumber: String) {
        invokeUseCase(detailUseCases.getStockIssuanceInfo(getBaseDate(), corpNumber), _uiState) { result ->
            _uiState.update { it.copy(data = _uiState.value.data.copy(stockIssuanceInfo = result.data?.get(0))) }
        }
    }

    private suspend fun getIndustryClassification(ksic: String) {
        invokeUseCase(detailUseCases.getIndustryClassificationByKSIC(ksic), _uiState) { result ->
            _uiState.update { it.copy(data = _uiState.value.data.copy(industryClassification = result.data)) }
        }
    }
}