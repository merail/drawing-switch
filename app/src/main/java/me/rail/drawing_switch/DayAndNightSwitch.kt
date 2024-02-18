package me.rail.drawing_switch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.rail.drawing_switch.ui.theme.DrawingSwitchTheme

@Composable
fun DayAndNightSwitch() {
    val isEnabled = remember {
        mutableStateOf(
            value = false,
        )
    }

    val disabledTrackColor = Color(
        red = 77,
        green = 77,
        blue = 77,
    )
    val disabledTrackStrokeColor = Color.Black
    val enabledTrackColor = Color(
        red = 179,
        green = 248,
        blue = 255,
    )
    val enabledTrackStrokeColor = Color(
        red = 1,
        green = 179,
        blue = 235,
    )
    val trackColor by animateColorAsState(
        targetValue = if (isEnabled.value) {
            enabledTrackColor
        } else {
            disabledTrackColor
        },
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "TrackColorAnimation",
    )
    val trackStrokeColor by animateColorAsState(
        targetValue = if (isEnabled.value) {
            enabledTrackStrokeColor
        } else {
            disabledTrackStrokeColor
        },
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "TrackStrokeColorAnimation",
    )

    val trackWidth = 234.dp
    val trackHeight = 109.dp
    val trackStrokeWidth = 6.dp

    val trackTranslation by animateDpAsState(
        targetValue = if (isEnabled.value) {
            -trackWidth / 2 - 7.dp
        } else {
            0.dp
        },
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "TrackColorAnimation",
    )

    Box(
        modifier = Modifier
            .padding(20.dp),
    ) {
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
                color = trackStrokeColor,
                topLeft = Offset(
                    x = (-trackStrokeWidth / 2).toPx(),
                    y = (-trackStrokeWidth / 2).toPx(),
                ),
                size = Size(
                    width = trackWidth.toPx() + trackStrokeWidth.toPx(),
                    height = trackHeight.toPx() + trackStrokeWidth.toPx(),
                ),
                cornerRadius = CornerRadius(
                    x = 58.dp.toPx(),
                    y = 58.dp.toPx(),
                ),
            )

            drawRoundRect(
                color = trackColor,
                topLeft = Offset(
                    x = (trackStrokeWidth / 2).toPx(),
                    y = (trackStrokeWidth / 2).toPx(),
                ),
                size = Size(
                    width = (trackWidth - trackStrokeWidth).toPx(),
                    height = (trackHeight - trackStrokeWidth).toPx(),
                ),
                cornerRadius = CornerRadius(
                    x = 52.dp.toPx(),
                    y = 52.dp.toPx(),
                ),
            )

            drawDisabledThumb(
                trackWidth = trackWidth,
                trackTranslation = trackTranslation.toPx(),
            )
        }
    }

}

private fun DrawScope.drawDisabledThumb(
    trackWidth: Dp,
    trackTranslation: Float,
) {
    translate(
        left = trackTranslation,
    ) {
        val thumbRadius = 45.dp
        val thumbStrokeRadius = 6.dp

        val disabledThumbStrokeColor = Color(
            red = 199,
            green = 199,
            blue = 172,
        )
        val disabledThumbColor = Color(
            red = 241,
            green = 241,
            blue = 241,
        )

        drawCircle(
            color = disabledThumbStrokeColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = (trackWidth - 10.dp - thumbRadius).toPx(),
                y = center.y,
            ),
            style = Stroke(
                width = thumbStrokeRadius.toPx(),
            ),
        )

        drawCircle(
            color = disabledThumbColor,
            radius = (thumbRadius - thumbStrokeRadius + 3.dp).toPx(),
            center = Offset(
                x = (trackWidth - 10.dp - thumbRadius).toPx(),
                y = center.y,
            ),
        )

        drawCircle(
            color = disabledThumbStrokeColor,
            radius = 7.dp.toPx(),
            center = Offset(
                x = 149.dp.toPx(),
                y = 29.dp.toPx(),
            ),
        )

        drawCircle(
            color = disabledThumbStrokeColor,
            radius = 10.dp.toPx(),
            center = Offset(
                x = 192.dp.toPx(),
                y = 23.dp.toPx(),
            ),
            style = Stroke(
                width = 6.dp.toPx(),
            ),
        )

        drawCircle(
            color = disabledThumbStrokeColor,
            radius = 6.dp.toPx(),
            center = Offset(
                x = 187.dp.toPx(),
                y = 84.dp.toPx(),
            ),
            style = Stroke(
                width = 6.dp.toPx(),
            ),
        )
    }
}

@Preview
@Composable
fun DayAndNightSwitchPreview() {
    DrawingSwitchTheme {
        DayAndNightSwitch()
    }
}