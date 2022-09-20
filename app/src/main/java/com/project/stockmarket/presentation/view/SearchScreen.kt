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
import androidx.navigation.NavController
import com.project.stockmarket.domain.model.KrxListedInfo
import com.project.stockmarket.presentation.Screen
import com.project.stockmarket.presentation.utils.KrxListedInfoState
import com.project.stockmarket.presentation.viewmodel.KrxListedInfoViewModel

@Composable
fun SearchScreen(
    viewModel: KrxListedInfoViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    Column {
        SearchBar(
            hint = "종목명을 입력하세요.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (it.isEmpty()) viewModel.setEmptyList() else viewModel.getKrxListedInfo(it)
        }
        AutoCompleteView(state, navController)
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
    state: KrxListedInfoState,
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            items(state.krxListedInfo) { krxListedInfo ->
                StockListItem(
                    krxListedInfo = krxListedInfo,
                    onItemClick = {
                        navController.navigate(Screen.DetailScreen.route + "/${krxListedInfo.corpNumber}")
                    }
                )
            }
        }
    }
}

@Composable
fun StockListItem(
    krxListedInfo: KrxListedInfo,
    onItemClick: (KrxListedInfo) -> Unit
) {
    Text(
        text = krxListedInfo.stockName,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(krxListedInfo) }
            .padding(10.dp)
    )
}