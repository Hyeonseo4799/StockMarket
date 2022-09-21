package com.project.stockmarket.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.usecase.GetStockIssuanceInfoUseCase
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.utils.StockIssuanceInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StockIssuanceInfoViewModel @Inject constructor(
    private val getStockIssuanceInfoUseCase: GetStockIssuanceInfoUseCase
) : BaseViewModel() {
    private val _state = mutableStateOf(StockIssuanceInfoState())
    val state: State<StockIssuanceInfoState> = _state

    private fun getStockIssuanceInfo(crno: String) {
        getStockIssuanceInfoUseCase(getBaseDate(), crno).onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _state.value = StockIssuanceInfoState(error = result.message ?: "An unexpected error occurred")
                }
                is NetworkResult.Loading -> {
                    _state.value = StockIssuanceInfoState(isLoading = true)
                }
                is NetworkResult.Success -> {
                    _state.value = StockIssuanceInfoState(stockIssuanceInfo = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}