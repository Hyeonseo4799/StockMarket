package com.project.stockmarket.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.usecase.GetKrxListedInfoUseCase
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.component.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val krxListedInfoUseCase: GetKrxListedInfoUseCase
) : BaseViewModel() {
    private val _state = MutableStateFlow(SearchUiState())
    val state: StateFlow<SearchUiState> = _state

    fun getKrxListedInfo(likeItmsNm: String) {
        viewModelScope.launch {
            krxListedInfoUseCase(getBaseDate(), likeItmsNm).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _state.value = state.value.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is NetworkResult.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                    is NetworkResult.Success -> {
                        _state.value = state.value.copy(
                            data = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun setEmptyList() {
        _state.value = SearchUiState(data = emptyList())
    }
}