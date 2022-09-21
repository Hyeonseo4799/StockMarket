package com.project.stockmarket.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.usecase.GetCorporationInfoUseCase
import com.project.stockmarket.domain.usecase.GetStockIssuanceInfoUseCase
import com.project.stockmarket.domain.usecase.GetStockPriceInfoUseCase
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.utils.CorporationInfoState
import com.project.stockmarket.presentation.utils.StockIssuanceInfoState
import com.project.stockmarket.presentation.utils.StockPriceInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val corporationInfoUseCase: GetCorporationInfoUseCase,
    private val stockPriceInfoUseCase: GetStockPriceInfoUseCase,
    private val stockIssuanceInfoUseCase: GetStockIssuanceInfoUseCase
) : BaseViewModel() {
    private val _stockPriceInfoState = mutableStateOf(StockPriceInfoState())
    val stockPriceInfoState: State<StockPriceInfoState> = _stockPriceInfoState

    private val _corporationInfoState = mutableStateOf(CorporationInfoState())
    val corporationInfoState: State<CorporationInfoState> = _corporationInfoState

    private val _stockIssuanceInfoState = mutableStateOf(StockIssuanceInfoState())
    val stockIssuanceInfoState: State<StockIssuanceInfoState> = _stockIssuanceInfoState

    fun getCorporationInfo(crno: String) {
        corporationInfoUseCase(getBaseDate(), crno).onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _corporationInfoState.value = CorporationInfoState(error = result.message ?: "An unexpected error occurred")
                }
                is NetworkResult.Loading -> {
                    _corporationInfoState.value = CorporationInfoState(isLoading = true)
                }
                is NetworkResult.Success -> {
                    _corporationInfoState.value = CorporationInfoState(corporationInfo = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getStockPriceInfo(isinCd: String) {
        stockPriceInfoUseCase(getBaseDate(), isinCd).onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _stockPriceInfoState.value = StockPriceInfoState(error = result.message ?: "An unexpected error occurred")
                }
                is NetworkResult.Loading -> {
                    _stockPriceInfoState.value = StockPriceInfoState(isLoading = true)
                }
                is NetworkResult.Success -> {
                    _stockPriceInfoState.value = StockPriceInfoState(stockPriceInfo = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getStockIssuanceInfo(crno: String) {
        stockIssuanceInfoUseCase(getBaseDate(), crno).onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _stockIssuanceInfoState.value = StockIssuanceInfoState(error = result.message ?: "An unexpected error occurred")
                }
                is NetworkResult.Loading -> {
                    _stockIssuanceInfoState.value = StockIssuanceInfoState(isLoading = true)
                }
                is NetworkResult.Success -> {
                    _stockIssuanceInfoState.value = StockIssuanceInfoState(stockIssuanceInfo = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}