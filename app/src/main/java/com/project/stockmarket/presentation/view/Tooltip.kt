package com.project.stockmarket.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.skgmn.composetooltip.AnchorEdge
import com.github.skgmn.composetooltip.EdgePosition
import com.github.skgmn.composetooltip.rememberTooltipStyle
import com.project.stockmarket.R

@Composable
fun Tooltip() {
    var visibility by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_tooltip),
                contentDescription = "tooltipIcon",
                modifier = Modifier
                    .clickable { visibility = true }
                    .align(Alignment.TopEnd)
            )
            if (visibility) {
                com.github.skgmn.composetooltip.Tooltip(
                    anchorEdge = AnchorEdge.Top,
                    tooltipStyle = rememberTooltipStyle(
                        color = com.project.stockmarket.presentation.ui.theme.Tooltip,
                        tipHeight = 5.dp,
                        tipWidth = 8.dp,
                        contentPadding = PaddingValues(8.dp)
                    ),
                    tipPosition = EdgePosition(1f),
                    onDismissRequest = { visibility = false },
                    margin = 2.dp
                ) {
                    Text(
                        text = "주가 데이터는 영업일 기준 하루 전 데이터입니다.",
                        color = Color.White,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}