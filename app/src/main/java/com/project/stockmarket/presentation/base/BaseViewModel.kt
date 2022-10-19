package com.project.stockmarket.presentation.base

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

open class BaseViewModel : ViewModel() {
    fun getBaseDate(): String {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyyMMdd", Locale.KOREA)

        when(calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> calendar.add(Calendar.DATE, -4)
            7 -> calendar.add(Calendar.DATE, -3)
            else -> calendar.add(Calendar.DATE, -2)
        }
        return sdf.format(calendar.time)
    }
}