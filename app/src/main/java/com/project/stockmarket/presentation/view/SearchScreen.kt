package com.project.stockmarket.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.stockmarket.domain.model.KrxListedInfo
import com.project.stockmarket.presentation.Screen
import com.project.stockmarket.presentation.utils.DataState
import com.project.stockmarket.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
    onBackPress: () -> Unit
) {
    val state = viewModel.state.value
    Column {
        SearchBar(
            hint = "종목명을 입력하세요.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onSearch = {
                if (state.data.isNotEmpty()) {
                    val company = state.data[0]
                    viewModel.setEmptyList()
                    navController.navigate(
                        Screen.DetailScreen.route + "/${company.isinCode}" + "/${company.corpNumber}"
                    )
                }
            }
        ) {
            if (it.isEmpty()) viewModel.setEmptyList() else viewModel.getKrxListedInfo(it)
        }
        AutoCompleteView(state, viewModel, navController)
    }
    BackButtonPress(onBackPress)
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: () -> Unit,
    onChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onChange(it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch()
            }),
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
    state: DataState<KrxListedInfo>,
    viewModel: SearchViewModel,
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
            items(state.data) { krxListedInfo ->
                StockListItem(
                    krxListedInfo = krxListedInfo,
                    onItemClick = {
                        viewModel.setEmptyList()
                        navController.navigate(Screen.DetailScreen.route + "/${krxListedInfo.isinCode}" + "/${krxListedInfo.corpNumber}")
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