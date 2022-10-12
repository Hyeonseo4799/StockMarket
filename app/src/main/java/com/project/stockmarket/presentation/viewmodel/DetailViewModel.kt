package com.project.stockmarket.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.repository.IndustryCodeRepositoryImpl
import com.project.stockmarket.domain.model.CorporationInfo
import com.project.stockmarket.domain.model.StockIssuanceInfo
import com.project.stockmarket.domain.model.StockPriceInfo
import com.project.stockmarket.domain.usecase.GetCorporationInfoUseCase
import com.project.stockmarket.domain.usecase.GetStockIssuanceInfoUseCase
import com.project.stockmarket.domain.usecase.GetStockPriceInfoUseCase
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val corporationInfoUseCase: GetCorporationInfoUseCase,
    private val stockPriceInfoUseCase: GetStockPriceInfoUseCase,
    private val stockIssuanceInfoUseCase: GetStockIssuanceInfoUseCase,
    private val repository: IndustryCodeRepositoryImpl
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
            executeUseCase(corporationInfoUseCase(getBaseDate(), crno), _corporationInfoState)
    }

    fun getStockPriceInfo(isinCd: String) {
        if (isEmpty(stockPriceInfoState))
            executeUseCase(stockPriceInfoUseCase(getBaseDate(), isinCd), _stockPriceInfoState)
    }

    fun getStockIssuanceInfo(crno: String) {
        if (isEmpty(stockIssuanceInfoState))
            executeUseCase(stockIssuanceInfoUseCase(getBaseDate(), crno), _stockIssuanceInfoState)
    }

    fun getIndustryClassificationByIndustryCode(industryCode: String) {
        viewModelScope.launch {
            repository.getIndustryClassificationByIndustryCode(industryCode).collect { result ->
                _industryClassificationState.value = result.data ?: industryCode
            }
        }
    }
}