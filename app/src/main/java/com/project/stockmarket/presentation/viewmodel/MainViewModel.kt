package com.project.stockmarket.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.NetworkResult
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
    private val _state = MutableStateFlow(DetailUiState())
    val state: StateFlow<DetailUiState> = _state

    init {
        getKSIC()
    }

    private fun getKSIC() {
        viewModelScope.launch {
            detailUseCases.getKSIC().collect { result ->
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
                            ksic = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun insertIndustryCode(KSIC: List<KSIC>) {
        viewModelScope.launch {
            KSIC.forEach {
                detailUseCases.setIndustryCode(it)
            }
        }
    }
}