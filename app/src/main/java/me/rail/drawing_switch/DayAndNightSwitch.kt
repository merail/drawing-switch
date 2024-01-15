package me.rail.drawing_switch

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.rail.drawing_switch.ui.theme.DrawingSwitchTheme

@Composable
fun DayAndNightSwitch() {
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

    val trackWidth = 234.dp
    val trackHeight = 109.dp
    val trackStrokeWidth = 6.dp

    val isEnabled = remember {
        mutableStateOf(
            value = false,
        )
    }

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
            size = Size(
                width = trackWidth.toPx(),
                height = trackHeight.toPx(),
            ),
            cornerRadius = CornerRadius(
                x = 100.dp.toPx(),
                y = 100.dp.toPx(),
            ),
            style = Stroke(
                width = trackStrokeWidth.toPx(),
            ),
        )

        drawRoundRect(
            color = trackColor,
            topLeft = Offset(
                x = 2.dp.toPx(),
                y = 2.dp.toPx(),
            ),
            size = Size(
                width = (trackWidth - 4.dp).toPx(),
                height = (trackHeight - 4.dp).toPx(),
            ),
            cornerRadius = CornerRadius(
                x = 100.dp.toPx(),
                y = 100.dp.toPx(),
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