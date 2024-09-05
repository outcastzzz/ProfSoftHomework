package com.example.togetherApp.presentation.core.components.switch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TSwitch(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    width: Dp = 42.dp,
    height: Dp = 22.dp,
    checkedColor: Color = Color.Black,
    uncheckedColor: Color = Color.Gray,
    thumbColor: Color = Color.Yellow
) {
    val switchPadding = 3.dp
    val thumbRadius = 8.dp
    val thumbOffsetX by animateDpAsState(
        targetValue = if (isChecked) width - thumbRadius * 2 - switchPadding * 2 else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = ""
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (isChecked) checkedColor else uncheckedColor,
        animationSpec = tween(durationMillis = 300),
        label = ""
    )

    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onCheckedChange(!isChecked) }
            .padding(switchPadding),
        contentAlignment = Alignment.CenterStart
    ) {
        Canvas(
            modifier = Modifier
                .size(thumbRadius * 2)
                .offset(x = thumbOffsetX)
        ) {
            drawCircle(color = thumbColor)
        }
    }
}

@Preview
@Composable
fun PreviewCustomSwitch() {
    var isChecked by remember { mutableStateOf(true) }
    TSwitch(
        isChecked = isChecked,
        onCheckedChange = { isChecked = it }
    )
}