package me.rail.drawing_switch.day_and_night_switch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.rail.drawing_switch.ui.theme.DrawingSwitchTheme

@Composable
fun DayAndNightSwitch() {
    val isEnabled = remember {
        mutableStateOf(
            value = false,
        )
    }
    val isTrackDecorationVisible = remember {
        mutableStateOf(
            value = false,
        )
    }
    val isThumbDecorationVisible = remember {
        mutableStateOf(
            value = true,
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
            durationMillis = 500,
            delayMillis = 1000,
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
            durationMillis = 500,
            delayMillis = 1000,
        ),
        label = "TrackStrokeColorAnimation",
    )
    val disabledThumbColor = Color(
        red = 241,
        green = 241,
        blue = 241,
    )
    val disabledThumbStrokeColor = Color(
        red = 199,
        green = 199,
        blue = 172,
    )
    val enabledThumbColor = Color(
        red = 251,
        green = 236,
        blue = 32,
    )
    val enabledThumbStrokeColor = Color(
        red = 255,
        green = 146,
        blue = 30,
    )
    val thumbColor by animateColorAsState(
        targetValue = if (isEnabled.value) {
            enabledThumbColor
        } else {
            disabledThumbColor
        },
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 1000,
        ),
        label = "ThumbColorAnimation",
    )
    val thumbStrokeColor by animateColorAsState(
        targetValue = if (isEnabled.value) {
            enabledThumbStrokeColor
        } else {
            disabledThumbStrokeColor
        },
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 1000,
        ),
        label = "ThumbStrokeColorAnimation",
    )

    val trackWidth = 234.dp
    val trackHeight = 109.dp
    val trackStrokeWidth = 6.dp
    val thumbRadius = 45.dp
    /**
     * Description is in [ColoredTrackSwitch]
     */
    val thumbPadding = 10.dp
    val thumbTranslation by animateDpAsState(
        targetValue = if (isEnabled.value) {
            -(trackWidth - thumbPadding - thumbRadius - thumbPadding - thumbRadius)
        } else {
            0.dp
        },
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "ThumbTranslation",
    ) {
        isTrackDecorationVisible.value = false
    }

    Box(
        modifier = Modifier
            .padding(20.dp),
    ) {
        val modifier = Modifier
            .size(
                width = trackWidth,
                height = trackHeight,
            )
            .clickable {
                isEnabled.value = !isEnabled.value
                isThumbDecorationVisible.value = !isEnabled.value
                if (isEnabled.value) {
                    isTrackDecorationVisible.value = true
                }
            }

        Track(
            modifier = modifier,
            trackWidth = trackWidth,
            trackHeight = trackHeight,
            trackStrokeWidth = trackStrokeWidth,
            trackStrokeColor = trackStrokeColor,
            trackColor = trackColor,
        )

        DisabledTrackDecoration(
            isTrackDecorationVisible = isTrackDecorationVisible,
            modifier = modifier,
        )

        Thumb(
            modifier = modifier,
            trackWidth = trackWidth,
            thumbRadius = thumbRadius,
            thumbTranslation = thumbTranslation,
            thumbPadding = thumbPadding,
            thumbColor = thumbColor,
            thumbStrokeColor = thumbStrokeColor,
        )

        ThumbDecoration(
            isThumbDecorationVisible = isThumbDecorationVisible,
            modifier = modifier,
            thumbDecorationColor = disabledThumbStrokeColor,
            thumbTranslation = thumbTranslation,
        )

        EnabledTrackDecoration(
            isTrackDecorationVisible = isTrackDecorationVisible,
            modifier = modifier,
            trackWidth = trackWidth,
            trackHeight = trackHeight,
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