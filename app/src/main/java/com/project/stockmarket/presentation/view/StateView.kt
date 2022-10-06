package com.project.stockmarket.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.stockmarket.presentation.ui.theme.Background
import com.project.stockmarket.presentation.utils.DataState

@Composable
fun <T> StateView(state: List<DataState<out T>>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        state.forEach {
            if (it.error.isNotBlank()) {
                Text(
                    text = it.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (it.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}