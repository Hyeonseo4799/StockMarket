package com.project.stockmarket.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.repository.IndustryCodeRepositoryImpl
import com.project.stockmarket.domain.model.KSIC
import com.project.stockmarket.domain.model.toKSICEntity
import com.project.stockmarket.domain.usecase.DetailUseCases
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val detailUseCases: DetailUseCases
) : BaseViewModel() {
    private val _ksicState = mutableStateOf(DataState<KSIC>())
    val industryCodeState: State<DataState<KSIC>> = _ksicState

    init {
        getKSIC()
    }

    private fun getKSIC() {
        if (industryCodeState.value.data.isEmpty()) {
            executeUseCase(detailUseCases.getKSIC(), _ksicState)
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