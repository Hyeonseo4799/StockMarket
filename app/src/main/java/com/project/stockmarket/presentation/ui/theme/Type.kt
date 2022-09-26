package com.project.stockmarket.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.project.stockmarket.R

val Pretendard = FontFamily(
    Font(R.font.pretendard_regular),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Pretendard,
        fontSize = 32.sp
    ),
    h2 = TextStyle(
        fontFamily = Pretendard,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = Pretendard,
        fontSize = 16.sp
    ),
    h4 = TextStyle(
        fontFamily = Pretendard,
        fontSize = 14.sp
    ),
    h5 = TextStyle(
        fontFamily = Pretendard,
        fontSize = 12.sp
    )
)