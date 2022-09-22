package com.project.stockmarket.presentation.utils

data class DataState<T>(
    val isLoading: Boolean = false,
    val data: List<T> = emptyList(),
    val error: String = ""
)
