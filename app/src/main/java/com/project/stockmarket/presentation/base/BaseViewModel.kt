package com.project.stockmarket.presentation.base

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

open class BaseViewModel : ViewModel() {
    fun getBaseDate(): String {
        val today = getCurrentDate()
        val currentTime = System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
        var baseDate = Integer.parseInt(simpleDateFormat.format(currentTime)) - 2

        if (today <= 2) baseDate -= today
        return baseDate.toString()
    }

    private fun getCurrentDate(): Int {
        val currentTime = System.currentTimeMillis()
        val date = Date(currentTime)
        val calendar = Calendar.getInstance()
        calendar.time = date

        return calendar[Calendar.DAY_OF_WEEK]
    }
}