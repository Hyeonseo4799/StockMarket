package com.project.stockmarket.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.usecase.GetCorporationInfoUseCase
import com.project.stockmarket.presentation.utils.CorporationInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CorporationInfoViewModel @Inject constructor(
    private val corporationInfoUseCase: GetCorporationInfoUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CorporationInfoState())
    val state: State<CorporationInfoState> = _state

    init {
        getCorporationInfo()
    }

    private fun getCorporationInfo() {
        corporationInfoUseCase().onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _state.value = CorporationInfoState(error = result.message ?: "An unexpected error occurred")
                }
                is NetworkResult.Loading -> {
                    _state.value = CorporationInfoState(isLoading = true)
                }
                is NetworkResult.Success -> {
                    _state.value = CorporationInfoState(corporationInfo = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}