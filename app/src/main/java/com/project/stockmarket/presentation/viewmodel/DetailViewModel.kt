package com.project.stockmarket.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.domain.model.CorporationInfo
import com.project.stockmarket.domain.model.StockIssuanceInfo
import com.project.stockmarket.domain.model.StockPriceInfo
import com.project.stockmarket.domain.usecase.DetailUseCases
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCases: DetailUseCases
) : BaseViewModel() {
    private val _stockPriceInfoState = mutableStateOf(DataState<StockPriceInfo>())
    val stockPriceInfoState: State<DataState<StockPriceInfo>> = _stockPriceInfoState

    private val _corporationInfoState = mutableStateOf(DataState<CorporationInfo>())
    val corporationInfoState: State<DataState<CorporationInfo>> = _corporationInfoState

    private val _stockIssuanceInfoState = mutableStateOf(DataState<StockIssuanceInfo>())
    val stockIssuanceInfoState: State<DataState<StockIssuanceInfo>> = _stockIssuanceInfoState

    private val _industryClassificationState = mutableStateOf("")
    val industryClassificationState: State<String> = _industryClassificationState

    fun getCorporationInfo(crno: String) {
        if (isEmpty(corporationInfoState))
            executeUseCase(detailUseCases.getCorporationInfo(getBaseDate(), crno), _corporationInfoState)
    }

    fun getStockPriceInfo(isinCd: String) {
        if (isEmpty(stockPriceInfoState))
            executeUseCase(detailUseCases.getStockPriceInfo(getBaseDate(), isinCd), _stockPriceInfoState)
    }

    fun getStockIssuanceInfo(crno: String) {
        if (isEmpty(stockIssuanceInfoState))
            executeUseCase(detailUseCases.getStockIssuanceInfo(getBaseDate(), crno), _stockIssuanceInfoState)
    }

    fun getIndustryClassificationByIndustryCode(industryCode: String) {
        if (industryClassificationState.value == "") {
            viewModelScope.launch {
                detailUseCases.getIndustryClassificationByKSIC(industryCode).collect { result ->
                    _industryClassificationState.value = result.data.toString()
                }
            }
        }
    }
}