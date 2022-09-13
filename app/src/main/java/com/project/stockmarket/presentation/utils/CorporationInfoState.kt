package com.project.stockmarket.presentation.utils

import com.project.stockmarket.domain.model.CorporationInfo

data class CorporationInfoState(
    val isLoading: Boolean = false,
    val corporationInfo: CorporationInfo? = null,
    val error: String = ""
)
