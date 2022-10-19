package com.project.stockmarket.presentation.component

import com.project.stockmarket.domain.model.KrxListedInfo

data class SearchUiState(
    val isLoading: Boolean = false,
    val data: List<KrxListedInfo> = emptyList(),
    val error: String = ""
)
