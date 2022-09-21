package com.project.stockmarket.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.usecase.GetStockPriceInfoUseCase
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.utils.StockPriceInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StockPriceInfoViewModel @Inject constructor(
    private val stockPriceInfoUseCase: GetStockPriceInfoUseCase
) : BaseViewModel() {
    private val _state = mutableStateOf(StockPriceInfoState())
    val state: State<StockPriceInfoState> = _state

    fun getStockPriceInfo(itmsNm: String) {
        stockPriceInfoUseCase(getBaseDate(), itmsNm).onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _state.value = StockPriceInfoState(error = result.message ?: "An unexpected error occurred")
                }
                is NetworkResult.Loading -> {
                    _state.value = StockPriceInfoState(isLoading = true)
                }
                is NetworkResult.Success -> {
                    _state.value = StockPriceInfoState(stockPriceInfo = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}