package com.project.stockmarket.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.repository.IndustryCodeRepositoryImpl
import com.project.stockmarket.domain.model.IndustryCode
import com.project.stockmarket.domain.model.toIndustryCodeEntity
import com.project.stockmarket.domain.usecase.GetKoreaStandardIndustryCodeUseCase
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val koreaStandardIndustryCodeUseCase: GetKoreaStandardIndustryCodeUseCase,
    private val repository: IndustryCodeRepositoryImpl
) : BaseViewModel() {
    private val _industryCodeState = mutableStateOf(DataState<IndustryCode>())
    val industryCodeState: State<DataState<IndustryCode>> = _industryCodeState

    init {
        getKoreaStandardIndustryCode()
    }

    private fun getKoreaStandardIndustryCode() {
        if (industryCodeState.value.data.isEmpty()) {
            executeUseCase(koreaStandardIndustryCodeUseCase(), _industryCodeState)
        }
    }

    fun insertIndustryCode(industryCode: List<IndustryCode>) {
        viewModelScope.launch {
            industryCode.forEach {
                repository.insertIndustryCode(it.toIndustryCodeEntity())
            }
        }
    }
}