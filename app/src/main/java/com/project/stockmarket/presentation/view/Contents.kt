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
import java.text.DecimalFormat

@Composable
fun Contents(
    corporationInfoState: CorporationInfo,
    stockPriceInfoState: StockPriceInfo,
    stockIssuanceInfoState: StockIssuanceInfo,
    industryClassification: String,
    showOnWebView: @Composable () -> Unit
) {
    Column {
        var showWebView by remember { mutableStateOf(false) }
        val context = LocalContext.current
        Spacer(modifier = Modifier.weight(1f))
        CardView(
            stockPriceInfo = stockPriceInfoState,
            industryClassification = industryClassification
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "????????? ??????",
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

                TableView("??????", "${dec.format(Integer.parseInt(stockPriceInfoState.highPrice))}???")
                TableView("??????", "${dec.format(Integer.parseInt(stockPriceInfoState.lowPrice))}???")
                TableView("?????????", "${dec.format(Integer.parseInt(stockPriceInfoState.openingPrice))}???")
                TableView("??????", "${dec.format(Integer.parseInt(stockPriceInfoState.closingPrice))}???")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .shadow(4.dp, RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.background, Shapes.large)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .weight(1f)
            ) {
                val marketCap = stockPriceInfoState.marketCap.toFloat() / 1E8
                val tradingValue = stockPriceInfoState.tradingValue.toFloat() / 1E6
                val stockPriceInfo = stockPriceInfoState.tradingVolume.toFloat() / 1E4
                val stockIssuanceInfo = stockIssuanceInfoState.commonStockOutStanding.toFloat() / 1E4

                val dec = DecimalFormat("#,###")
                TableView("????????????", "${dec.format(marketCap)}??????")
                TableView("????????????", "${dec.format(tradingValue)}??????")
                TableView("?????????", "${dec.format(stockPriceInfo)}??????")
                TableView("?????????", "${dec.format(stockIssuanceInfo)}??????")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "??????????????? ??????",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.h4,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))
        val auditReportOpinion = corporationInfoState.auditReportOpinion
        Text(
            text = if (auditReportOpinion != "") auditReportOpinion else "????????????",
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
            text = "????????????",
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
                text = "???????????? ????????????",
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
                text = "?????? : ${corporationInfoState.address}",
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.h5,
                color = DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "???????????? : ${corporationInfoState.establishment}",
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.h5,
                color = DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "??????????????? : ${corporationInfoState.ownerName}",
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.h5,
                color = DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))

            val tel = corporationInfoState.telNumber
            val annotationText = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, color = DarkGray)) {
                    append("?????????????????? : ")
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
                text = annotationText,
                style = MaterialTheme.typography.h5,
                onClick = { offset ->
                    annotationText.getStringAnnotations(tag = "PHONE_NUMBER", start = offset, end = offset)
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