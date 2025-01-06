package com.bokju.amian.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun dashedModifier(color: Color, radius: Dp) = Modifier.drawBehind {
    drawRoundRect(
        color = color,
        style = Stroke(
            width = 2.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(10f, 10f),
                0f
            )
        ),
        cornerRadius = CornerRadius(radius.toPx(), radius.toPx())
    )
}