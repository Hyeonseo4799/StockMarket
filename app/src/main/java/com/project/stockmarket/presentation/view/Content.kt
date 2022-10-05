package com.project.stockmarket.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.stockmarket.domain.model.StockPriceInfo
import com.project.stockmarket.presentation.ui.theme.Background
import com.project.stockmarket.presentation.ui.theme.Shapes
import com.project.stockmarket.presentation.ui.theme.TextPrimary
import com.project.stockmarket.presentation.ui.theme.TextBackground
import java.text.DecimalFormat
import kotlin.math.sign

@Composable
fun Content(stockPriceInfo: StockPriceInfo, industryCode: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 26.dp)
                .shadow(5.dp, Shapes.large)
                .background(MaterialTheme.colors.background, Shapes.large),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
            ) {
                val dec = DecimalFormat("#,###")
                Text(
                    text = dec.format(Integer.parseInt(stockPriceInfo.closingPrice)),
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.alignByBaseline(),
                )
                Text(
                    text = "KRW",
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .alignByBaseline(),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                )
            }

            val priceChangeRate = checkSign(stockPriceInfo.priceChangeRate.toFloat())

            Text(
                text = priceChangeRate,
                color = when (stockPriceInfo.priceChangeRate.toFloat().sign) {
                    1.0f -> Color.Red
                    -1.0f -> Color.Blue
                    else -> Color.Gray
                },
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = industryCode,
                    color = TextPrimary,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .background(TextBackground, Shapes.medium)
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
                Text(
                    text = stockPriceInfo.ticker,
                    color = TextPrimary,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .background(TextBackground, Shapes.medium)
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

fun checkSign(number: Float): String {
    val priceChangeRate = if (number.sign == 1.0f) "+$number%" else "$number%"
    return priceChangeRate
        .replace("-.", "-0.")
        .replace("+.", "+0.")
}