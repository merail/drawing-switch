package me.rail.drawing_switch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.rail.drawing_switch.ui.theme.DrawingSwitchTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private val thumbRadius = 59.dp

@Composable
fun MathematicalSwitch() {
    val disabledColor = Color(
        red = 121,
        green = 121,
        blue = 121,
    )
    val enabledColor = Color(
        red = 1,
        green = 94,
        blue = 90,
    )

    val trackWidth = 234.dp
    val trackHeight = 134.dp

    val isEnabled = remember {
        mutableStateOf(
            value = false,
        )
    }

    val color by animateColorAsState(
        targetValue = if (isEnabled.value) {
            enabledColor
        } else {
            disabledColor
        },
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "ColorAnimation",
    )

    val thumbXPosition by animateDpAsState(
        targetValue = if (isEnabled.value) {
            trackWidth - (thumbRadius + 8.dp)
        } else {
            thumbRadius + 8.dp
        },
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "ThumbXPositionAnimation",
    )

    val plusAngleSine by animateFloatAsState(
        targetValue = (if (isEnabled.value) {
            sin(90 * PI / 180)
        } else {
            sin(0 * PI / 180)
        }).toFloat(),
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "PlusYPositionAnimation",
    )
    val plusAngleCosine by animateFloatAsState(
        targetValue = (if (isEnabled.value) {
            cos(90 * PI / 180)
        } else {
            cos(0 * PI / 180)
        }).toFloat(),
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "PlusYPositionAnimation",
    )

    Canvas(
        modifier = Modifier
            .size(
                width = trackWidth,
                height = trackHeight,
            )
            .clickable {
                isEnabled.value = !isEnabled.value
            },
    ) {
        drawRoundRect(
            color = color,
            cornerRadius = CornerRadius(
                x = 100.dp.toPx(),
                y = 100.dp.toPx(),
            ),
        )

        drawThumb(
            drawScope = this,
            color = color,
            thumbXPosition = thumbXPosition,
            plusAngleSine = plusAngleSine,
            plusAngleCosine = plusAngleCosine,
        )
    }
}

private fun drawThumb(
    drawScope: DrawScope,
    color: Color,
    thumbXPosition: Dp,
    plusAngleSine: Float,
    plusAngleCosine: Float,
) = with(drawScope) {
    val minusWidth = 62.dp
    val minusHeight = 10.dp

    drawCircle(
        color = Color.White,
        radius = thumbRadius.toPx(),
        center = Offset(
            x = thumbXPosition.toPx(),
            y = center.y,
        )
    )

    drawLine(
        color = color,
        start = Offset(
            x = (thumbXPosition - minusWidth / 2).toPx(),
            y = center.y,
        ),
        end = Offset(
            x = (thumbXPosition + minusWidth / 2).toPx(),
            y = center.y,
        ),
        strokeWidth = minusHeight.toPx(),
        cap = StrokeCap.Round,
    )

    drawLine(
        color = color,
        start = Offset(
            x = thumbXPosition.toPx() - plusAngleCosine * (minusWidth / 2).toPx(),
            y = center.y - (minusWidth / 2).toPx() * plusAngleSine,
        ),
        end = Offset(
            x = thumbXPosition.toPx() + plusAngleCosine * (minusWidth / 2).toPx(),
            y = center.y + (minusWidth / 2).toPx() * plusAngleSine,
        ),
        strokeWidth = minusHeight.toPx(),
        cap = StrokeCap.Round,
    )
}

@Preview
@Composable
fun MathematicalSwitchPreview() {
    DrawingSwitchTheme {
        MathematicalSwitch()
    }
}