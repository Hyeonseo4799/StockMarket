package com.project.stockmarket.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.usecase.GetKrxListedInfoUseCase
import com.project.stockmarket.presentation.utils.KrxListedInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class KrxListedInfoViewModel @Inject constructor(
    private val krxListedInfoUseCase: GetKrxListedInfoUseCase
) : ViewModel() {
    private val _state = mutableStateOf(KrxListedInfoState())
    val state: State<KrxListedInfoState> = _state

    fun getKrxListedInfo(likeItmsNm: String) {
        krxListedInfoUseCase(getCurrentDate(), likeItmsNm).onEach { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _state.value = KrxListedInfoState(error = result.message ?: "An unexpected error occurred")
                }
                is NetworkResult.Loading -> {
                    _state.value = KrxListedInfoState(isLoading = true)
                }
                is NetworkResult.Success -> {
                    _state.value = KrxListedInfoState(krxListedInfo = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCurrentDate(): String {
        val currentTime = System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
        val baseDate = Integer.parseInt(simpleDateFormat.format(currentTime)) - 3
        return baseDate.toString()
    }
}