package com.project.stockmarket.presentation.utils

import com.project.stockmarket.domain.model.KrxListedInfo

data class KrxListedInfoState(
    val isLoading: Boolean = false,
    val krxListedInfo: List<KrxListedInfo> = emptyList(),
    val error: String = ""
)