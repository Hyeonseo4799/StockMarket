package com.project.stockmarket.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.project.stockmarket.domain.model.KrxListedInfo
import com.project.stockmarket.domain.usecase.GetKrxListedInfoUseCase
import com.project.stockmarket.presentation.base.BaseViewModel
import com.project.stockmarket.presentation.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val krxListedInfoUseCase: GetKrxListedInfoUseCase
) : BaseViewModel() {
    private val _state = mutableStateOf(DataState<KrxListedInfo>())
    val state: State<DataState<KrxListedInfo>> = _state

    fun getKrxListedInfo(likeItmsNm: String) {
        executeUseCase(krxListedInfoUseCase(getBaseDate(), likeItmsNm), _state)
    }

    fun setEmptyList() {
        _state.value = DataState(data = emptyList())
    }
}