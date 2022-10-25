package com.project.stockmarket.presentation.base

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.project.stockmarket.data.NetworkResult
import com.project.stockmarket.domain.model.CorporationInfo
import com.project.stockmarket.domain.model.StockIssuanceInfo
import com.project.stockmarket.domain.model.StockPriceInfo
import com.project.stockmarket.domain.usecase.DetailUseCases
import com.project.stockmarket.presentation.component.DetailData
import com.project.stockmarket.presentation.component.DetailUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.*

open class BaseViewModel : ViewModel() {
    fun getBaseDate(): String {
        return "20221021"
//        val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA)
//        val sdf = SimpleDateFormat("yyyyMMdd", Locale.KOREAN)
//
//        Log.d(TAG, Calendar.DAY_OF_WEEK.toString())
//        when(calendar.get(Calendar.DAY_OF_WEEK - 2)) {
//            Calendar.SUNDAY -> calendar.add(Calendar.DATE, -2)
//            Calendar.SATURDAY -> calendar.add(Calendar.DATE, -1)
//            else -> calendar.add(Calendar.DATE, -2)
//        }
//        return sdf.format(calendar.time)
    }

    suspend fun <T> invokeUseCase(
        useCase: Flow<NetworkResult<T>>,
        uiState: MutableStateFlow<DetailUiState>,
        updateUiState: (NetworkResult<T>) -> Unit
    ) {
        useCase.collect { result ->
            when (result) {
                is NetworkResult.Error -> uiState.update { it.copy(error = result.message ?: "Unknown Error") }
                is NetworkResult.Success -> updateUiState(result)
            }
        }
    }
}
