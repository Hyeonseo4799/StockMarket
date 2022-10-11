package com.project.stockmarket.presentation.base

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.presentation.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*

open class BaseViewModel : ViewModel() {
    fun getBaseDate(): String {
        // 날짜 계산 로직
//        val today = getCurrentDate()
//        val currentTime = System.currentTimeMillis()
//        val simpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
//        var baseDate = Integer.parseInt(simpleDateFormat.format(currentTime)) - 2
//
//        if (today <= 3) {
//            baseDate -= (today - 1)
//        }
//        if (baseDate % 10 == 0)
//            baseDate -= 70
//        return baseDate.toString()
        return "20220930"
    }

    private fun getCurrentDate(): Int {
        val currentTime = System.currentTimeMillis()
        val date = Date(currentTime)
        val calendar = Calendar.getInstance()
        calendar.time = date

        return calendar[Calendar.DAY_OF_WEEK]
    }

    fun <T> executeUseCase(data: Flow<NetworkResult<List<T>>>, _state: MutableState<DataState<T>>) {
        data.onEach { result ->
           when (result) {
               is NetworkResult.Error -> {
                    _state.value = DataState(error = result.message ?: "An unexpected error occurred")
               }
               is NetworkResult.Loading -> {
                    _state.value = DataState(isLoading = true)
               }
               is NetworkResult.Success -> {
                    _state.value = DataState(data = result.data ?: emptyList())
               }
           }
        }.launchIn(viewModelScope)
    }

    fun <T> isEmpty(state: State<DataState<T>>): Boolean {
        return state.value.data.isEmpty()
    }
}