package me.rail.drawing_switch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.rail.drawing_switch.ui.theme.DrawingSwitchTheme

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
            brush = SolidColor(
                value = color,
            ),
            cornerRadius = CornerRadius(
                x = 100.dp.toPx(),
                y = 100.dp.toPx(),
            ),
        )

        drawThumb(
            drawScope = this,
            thumbXPosition = thumbXPosition,
            color = color,
        )
    }
}

private fun drawThumb(
    drawScope: DrawScope,
    thumbXPosition: Dp,
    color: Color,
) = with(drawScope) {
    val minusWidth = 28.dp
    val minusHeight = 10.dp

    drawCircle(
        brush = SolidColor(
            value = Color.White,
        ),
        radius = thumbRadius.toPx(),
        center = Offset(
            x = thumbXPosition.toPx(),
            y = center.y,
        )
    )

    drawLine(
        color = color,
        start = Offset(
            x = (thumbXPosition - thumbRadius + minusWidth).toPx(),
            y = center.y,
        ),
        end = Offset(
            x = (thumbXPosition + minusWidth).toPx(),
            y = center.y,
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