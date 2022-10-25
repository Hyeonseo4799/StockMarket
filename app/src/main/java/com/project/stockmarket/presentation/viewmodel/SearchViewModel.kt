package com.project.stockmarket.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.usecase.GetKrxListedInfoUseCase
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.component.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val krxListedInfoUseCase: GetKrxListedInfoUseCase
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    fun getKrxListedInfo(likeItmsNm: String) {
        viewModelScope.launch {
            krxListedInfoUseCase(getBaseDate(), likeItmsNm).collect { result ->
                when(result) {
                    is NetworkResult.Error -> _uiState.update { it.copy(error = result.message ?: "Unknown Error") }
                    is NetworkResult.Success -> _uiState.update { it.copy(data = result.data ?: emptyList()) }
                }
            }
        }
    }

    fun setEmptyList() {
        _uiState.value = SearchUiState(data = emptyList())
    }
}