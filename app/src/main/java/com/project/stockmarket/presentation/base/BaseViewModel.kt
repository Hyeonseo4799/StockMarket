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
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyyMMdd", Locale.KOREA)

        when(calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> calendar.add(Calendar.DATE, -3)
            7 -> calendar.add(Calendar.DATE, -2)
            else -> calendar.add(Calendar.DATE, -1)
        }
        return sdf.format(calendar.time)
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