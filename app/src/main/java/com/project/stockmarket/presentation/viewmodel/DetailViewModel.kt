package com.project.stockmarket.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.usecase.DetailUseCases
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.component.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCases: DetailUseCases
) : BaseViewModel() {
    private val _state = MutableStateFlow(DetailUiState())
    val state: StateFlow<DetailUiState> = _state

    fun getCorporationInfo(crno: String) {
        viewModelScope.launch {
            detailUseCases.getCorporationInfo(getBaseDate(), crno).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _state.value = state.value.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is NetworkResult.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                    is NetworkResult.Success -> {
                        _state.value = state.value.copy(
                            corporationInfo = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun getStockPriceInfo(isinCd: String) {
        viewModelScope.launch {
            detailUseCases.getStockPriceInfo(getBaseDate(), isinCd).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _state.value = state.value.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is NetworkResult.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                    is NetworkResult.Success -> {
                        _state.value = state.value.copy(
                            stockPriceInfo = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun getStockIssuanceInfo(crno: String) {
        viewModelScope.launch {
            detailUseCases.getStockIssuanceInfo(getBaseDate(), crno).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _state.value = state.value.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is NetworkResult.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                    is NetworkResult.Success -> {
                        _state.value = state.value.copy(
                            stockIssuanceInfo = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun getIndustryClassificationByKSIC(ksic: String) {
        viewModelScope.launch {
            detailUseCases.getIndustryClassificationByKSIC(ksic).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _state.value = state.value.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is NetworkResult.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                    is NetworkResult.Success -> {
                        _state.value = state.value.copy(
                            industryClassification = result.data,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}