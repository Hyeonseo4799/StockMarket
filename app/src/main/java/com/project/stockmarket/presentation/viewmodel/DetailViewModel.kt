package com.project.stockmarket.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.project.stockmarket.domain.model.CorporationInfo
import com.project.stockmarket.domain.model.KoreaStandardIndustryCode
import com.project.stockmarket.domain.model.StockIssuanceInfo
import com.project.stockmarket.domain.model.StockPriceInfo
import com.project.stockmarket.domain.usecase.GetCorporationInfoUseCase
import com.project.stockmarket.domain.usecase.GetKoreaStandardIndustryCodeUseCase
import com.project.stockmarket.domain.usecase.GetStockIssuanceInfoUseCase
import com.project.stockmarket.domain.usecase.GetStockPriceInfoUseCase
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val corporationInfoUseCase: GetCorporationInfoUseCase,
    private val stockPriceInfoUseCase: GetStockPriceInfoUseCase,
    private val stockIssuanceInfoUseCase: GetStockIssuanceInfoUseCase,
    private val koreaStandardIndustryCodeUseCase: GetKoreaStandardIndustryCodeUseCase
) : BaseViewModel() {
    private val _stockPriceInfoState = mutableStateOf(DataState<StockPriceInfo>())
    val stockPriceInfoState: State<DataState<StockPriceInfo>> = _stockPriceInfoState

    private val _corporationInfoState = mutableStateOf(DataState<CorporationInfo>())
    val corporationInfoState: State<DataState<CorporationInfo>> = _corporationInfoState

    private val _stockIssuanceInfoState = mutableStateOf(DataState<StockIssuanceInfo>())
    val stockIssuanceInfoState: State<DataState<StockIssuanceInfo>> = _stockIssuanceInfoState

    private val _koreaStandardIndustryCodeState = mutableStateOf(DataState<KoreaStandardIndustryCode>())
    val koreaStandardIndustryCodeState: State<DataState<KoreaStandardIndustryCode>> = _koreaStandardIndustryCodeState

    fun getCorporationInfo(crno: String) {
        executeUseCase(corporationInfoUseCase(getBaseDate(), crno), _corporationInfoState)
    }

    fun getStockPriceInfo(isinCd: String) {
        executeUseCase(stockPriceInfoUseCase(getBaseDate(), isinCd), _stockPriceInfoState)
    }

    fun getStockIssuanceInfo(crno: String) {
        executeUseCase(stockIssuanceInfoUseCase(getBaseDate(), crno), _stockIssuanceInfoState)
    }

    fun getKoreaStandardIndustryCode() {
        executeUseCase((koreaStandardIndustryCodeUseCase()), _koreaStandardIndustryCodeState)
    }
}