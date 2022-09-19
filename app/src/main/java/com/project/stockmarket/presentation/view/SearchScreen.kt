package com.project.stockmarket.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.stockmarket.presentation.utils.KrxListedInfoState
import com.project.stockmarket.presentation.viewmodel.KrxListedInfoViewModel

@Composable
fun SearchScreen(
    viewModel: KrxListedInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column {
        SearchBar(
            hint = "종목명을 입력하세요.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            viewModel.getKrxListedInfo(it)
        }
        AutoCompleteView(state)
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        BasicTextField(
            value = text, onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun AutoCompleteView(
    state: KrxListedInfoState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(300.dp)
        ) {
            items(state.krxListedInfo) { stockInfo ->
                Text(
                    text = stockInfo.stockName,
                    modifier = Modifier
                        .clickable {
                            // 세부 페이지 이동 코드
                        }
                        .padding(vertical = 10.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}