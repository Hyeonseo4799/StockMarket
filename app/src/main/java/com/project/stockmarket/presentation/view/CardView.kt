package com.project.stockmarket.presentation.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.skgmn.composetooltip.AnchorEdge
import com.github.skgmn.composetooltip.EdgePosition
import com.github.skgmn.composetooltip.Tooltip
import com.github.skgmn.composetooltip.rememberTooltipStyle
import com.project.stockmarket.R
import com.project.stockmarket.domain.model.StockPriceInfo
import com.project.stockmarket.presentation.ui.theme.Shapes
import com.project.stockmarket.presentation.ui.theme.TextPrimary
import com.project.stockmarket.presentation.ui.theme.TextBackground
import java.text.DecimalFormat
import kotlin.math.sign

@Composable
fun CardView(stockPriceInfo: StockPriceInfo, industryClassification: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, Shapes.large)
            .background(MaterialTheme.colors.background, Shapes.large),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var visibility by remember { mutableStateOf(true) }
        Box(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_tooltip),
                    contentDescription = "tooltipIcon",
                    modifier = Modifier.clickable { visibility = true }
                )
                if (visibility) {
                    Tooltip(
                        anchorEdge = AnchorEdge.Top,
                        tooltipStyle = rememberTooltipStyle(
                            color = Color(0x80000000),
                            tipHeight = 5.dp,
                            tipWidth = 8.dp,
                            contentPadding = PaddingValues(8.dp)
                        ),
                        tipPosition = EdgePosition(1f),
                        onDismissRequest = {
                            Log.d("visibility", "false")
                            visibility = false
                        },
                        margin = 2.dp
                    )
                    {
                        Text(
                            text = "주가 데이터는 영업일 기준 하루 전 데이터입니다.",
                            color = Color.White,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
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
        }
        val priceChangeRate = stockPriceInfo.priceChangeRate.toFloat()
        Text(
            text = checkSign(priceChangeRate),
            color = when (priceChangeRate.sign) {
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
                text = industryClassification,
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

fun checkSign(number: Float): String {
    val priceChangeRate = if (number.sign == 1.0f) "+$number%" else "$number%"
    return priceChangeRate
        .replace("-.", "-0.")
        .replace("+.", "+0.")
}