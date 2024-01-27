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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.rail.drawing_switch.ui.theme.DrawingSwitchTheme

@Composable
fun ColoredTrackSwitch() {
    val disabledTrackColor = Color(
        red = 234,
        green = 119,
        blue = 92,
    )
    val thumbColor = Color(
        red = 229,
        green = 229,
        blue = 229,
    )
    val enabledTrackColor = Color(
        red = 57,
        green = 189,
        blue = 59,
    )

    val trackWidth = 234.dp
    val trackHeight = 100.dp
    val thumbRadius = 43.dp
    val thumbPadding = 9.dp

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

    val thumbXPosition by animateDpAsState(
        targetValue = if (isEnabled.value) {
            trackWidth - (thumbRadius + thumbPadding)
        } else {
            thumbRadius + thumbPadding
        },
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "ThumbXPositionAnimation",
    )

    Box(
        modifier = Modifier
            .padding(20.dp)
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
                color = trackColor,
                cornerRadius = CornerRadius(
                    x = 60.dp.toPx(),
                    y = 60.dp.toPx(),
                ),
            )

            drawCircle(
                color = thumbColor,
                radius = thumbRadius.toPx(),
                center = Offset(
                    x = thumbXPosition.toPx(),
                    y = center.y,
                )
            )
        }
    }
}

@Preview
@Composable
fun ColoredTrackSwitchPreview() {
    DrawingSwitchTheme {
        ColoredTrackSwitch()
    }
}