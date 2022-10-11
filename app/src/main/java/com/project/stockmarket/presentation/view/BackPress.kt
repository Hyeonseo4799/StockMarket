package com.project.stockmarket.presentation.view

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun BackButtonPress(onBackPress: () -> Unit) {
    var backPressedTime: Long = 0
    val context = LocalContext.current

    BackHandler {
        if (backPressedTime + 3000 > System.currentTimeMillis())
            onBackPress()
        else
            Toast.makeText(context, "뒤로가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()

        backPressedTime = System.currentTimeMillis()
    }
}
