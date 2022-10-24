package com.project.stockmarket.presentation.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.domain.usecase.DetailUseCases
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.component.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
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
            getCorporationInfo(corpNumber)
            getStockPriceInfo(isinCode)
            getStockIssuanceInfo(corpNumber)

            if (_uiState.value.data.corporationInfo != null) {
                getIndustryClassification(_uiState.value.data.corporationInfo!!.IndustryCode)
            }
        }
    }

    private suspend fun getCorporationInfo(corpNumber: String) {
        detailUseCases.getCorporationInfo(getBaseDate(), corpNumber).collect { result ->
            try {
                _uiState.update { it.copy(data = _uiState.value.data.copy(corporationInfo = result.data?.get(0))) }
            } catch (e: Exception) {
                _uiState.value.error = e.localizedMessage ?: "Unknown Error"
            }
        }
    }

    private suspend fun getStockPriceInfo(isinCode: String) {
        detailUseCases.getStockPriceInfo(getBaseDate(), isinCode).collect { result ->
            try {
                _uiState.update { it.copy(data = _uiState.value.data.copy(stockPriceInfo = result.data?.get(0))) }
            } catch (e: Exception) {
                _uiState.value.error = e.localizedMessage ?: "Unknown Error"
            }
        }

    }

    private suspend fun getStockIssuanceInfo(corpNumber: String) {
        detailUseCases.getStockIssuanceInfo(getBaseDate(), corpNumber).collect { result ->
            try {
                _uiState.update { it.copy(data = _uiState.value.data.copy(stockIssuanceInfo = result.data?.get(0))) }
            } catch (e: Exception) {
                _uiState.value.error = e.localizedMessage ?: "Unknown Error"
            }

        }
    }

    private suspend fun getIndustryClassification(ksic: String) {
        detailUseCases.getIndustryClassificationByKSIC(ksic).collect { result ->
            try {
                _uiState.update { it.copy(data = _uiState.value.data.copy(industryClassification = result.data)) }
            } catch (e: Exception) {
                _uiState.value.error = e.localizedMessage ?: "Unknown Error"
            }

        }
    }
}