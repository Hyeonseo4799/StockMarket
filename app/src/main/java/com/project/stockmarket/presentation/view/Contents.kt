package com.project.stockmarket.presentation.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.project.stockmarket.R
import com.project.stockmarket.domain.model.CorporationInfo
import com.project.stockmarket.domain.model.StockIssuanceInfo
import com.project.stockmarket.domain.model.StockPriceInfo
import com.project.stockmarket.presentation.ui.theme.DarkGray
import com.project.stockmarket.presentation.ui.theme.Shapes
import com.project.stockmarket.presentation.utils.DataState
import java.text.DecimalFormat

@Composable
fun Contents(
    corporationInfoState: DataState<CorporationInfo>,
    stockPriceInfoState: DataState<StockPriceInfo>,
    stockIssuanceInfoState: DataState<StockIssuanceInfo>,
    industryClassification: String,
    showOnWebView: @Composable () -> Unit
) {
    Column {
        var showWebView by remember { mutableStateOf(false) }
        val context = LocalContext.current
        Spacer(modifier = Modifier.weight(1f))
        CardView(
            stockPriceInfo = stockPriceInfoState.data[0],
            industryClassification = industryClassification
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "데이터 요약",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.h4,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .shadow(4.dp, RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.background, Shapes.large)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .weight(1f)
            ) {
                val dec = DecimalFormat("#,###")
                val data = stockPriceInfoState.data[0]

                TableView("고가", "${dec.format(Integer.parseInt(data.highPrice))}원")
                TableView("저가", "${dec.format(Integer.parseInt(data.lowPrice))}원")
                TableView("시초가", "${dec.format(Integer.parseInt(data.openingPrice))}원")
                TableView("종가", "${dec.format(Integer.parseInt(data.closingPrice))}원")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .shadow(4.dp, RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.background, Shapes.large)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .weight(1f)
            ) {
                val stockPriceInfoData = stockPriceInfoState.data[0]
                val stockIssuanceInfoData = stockIssuanceInfoState.data[0]

                val marketCap = stockPriceInfoData.marketCap.toFloat() / 1E8
                val tradingValue = stockPriceInfoData.tradingValue.toFloat() / 1E6
                val stockPriceInfo = stockPriceInfoData.tradingVolume.toFloat() / 1E4
                val stockIssuanceInfo = stockIssuanceInfoData.commonStockOutStanding.toFloat() / 1E4

                val dec = DecimalFormat("#,###")
                TableView("시가총액", "${dec.format(marketCap)}억원")
                TableView("거래대금", "${dec.format(tradingValue)}백만")
                TableView("거래량", "${dec.format(stockPriceInfo)}만주")
                TableView("주식수", "${dec.format(stockIssuanceInfo)}만주")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "감사보고서 의견",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.h4,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))
        val auditReportOpinion = corporationInfoState.data[0].auditReportOpinion
        Text(
            text = if (auditReportOpinion != "") auditReportOpinion else "의견없음",
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .shadow(5.dp, Shapes.large)
                .background(MaterialTheme.colors.background, Shapes.large)
                .padding(12.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "세부정보",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.h4,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, Shapes.large)
                .background(MaterialTheme.colors.background, Shapes.large)
                .clickable { showWebView = true }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_home),
                contentDescription = "homepage",
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "홈페이지 바로가기",
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "move",
                modifier = Modifier
                    .align(Alignment.Bottom)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, Shapes.large)
                .background(MaterialTheme.colors.background, Shapes.large)
                .padding(12.dp)
        ) {
            Text(
                text = "위치 : ${corporationInfoState.data[0].address}",
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.h5,
                color = DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "설립일자 : ${corporationInfoState.data[0].establishment}",
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.h5,
                color = DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "기업대표자 : ${corporationInfoState.data[0].ownerName}",
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.h5,
                color = DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))

            val tel = corporationInfoState.data[0].telNumber
            val annotaionText = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, color = DarkGray)) {
                    append("기업전화번호 : ")
                }
                pushStringAnnotation(tag = "PHONE_NUMBER", annotation = tel)
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Medium,
                        color = DarkGray,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(tel)
                }
                pop()
            }
            ClickableText(
                text = annotaionText,
                style = MaterialTheme.typography.h5,
                onClick = { offset ->
                    annotaionText.getStringAnnotations(tag = "PHONE_NUMBER", start = offset, end = offset)
                        .firstOrNull()?.let { annotaion ->
                            openDial(tel = annotaion.item, context = context)
                        }
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        if (showWebView) {
            showWebView = false
            showOnWebView()
        }
    }
}

@Composable
fun TableView(title: String, content: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(vertical = 5.dp),
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.h5
        )
        Text(
            text = content,
            modifier = Modifier.padding(vertical = 5.dp),
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.h5,
            color = Color.Gray
        )
    }
}

fun openDial(tel: String, context: Context) {
    val uri = Uri.parse("tel:$tel")
    val intent = Intent(Intent.ACTION_DIAL, uri)
    context.startActivity(intent)
}