package me.rail.drawing_switch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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

private val trackWidth = 234.dp
private val trackHeight = 109.dp

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

    val trackStrokeWidth = 6.dp
    /**
     * Description is in [ColoredTrackSwitch]
     */
    val thumbPadding = 10.dp
    val thumbTranslation by animateDpAsState(
        targetValue = if (isEnabled.value) {
            -trackWidth / 2 - thumbPadding
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
            trackStrokeWidth = trackStrokeWidth,
            trackStrokeColor = trackStrokeColor,
            trackColor = trackColor,
        )

        TrackDecoration(
            isTrackDecorationVisible = isTrackDecorationVisible,
            modifier = modifier,
        )

        Thumb(
            modifier = modifier,
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
    }
}

@Composable
fun Track(
    modifier: Modifier,
    trackStrokeWidth: Dp,
    trackStrokeColor: Color,
    trackColor: Color,
) {
    Canvas(modifier) {
        drawRoundRect(
            color = trackStrokeColor,
            size = Size(
                width = trackWidth.toPx(),
                height = trackHeight.toPx(),
            ),
            cornerRadius = CornerRadius(trackHeight.toPx()),
            style = Stroke(
                width = trackStrokeWidth.toPx(),
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
            cornerRadius = CornerRadius(trackHeight.toPx()),
        )
    }
}

@Composable
fun TrackDecoration(
    isTrackDecorationVisible: MutableState<Boolean>,
    modifier: Modifier,
) {
    AnimatedVisibility(
        visible = isTrackDecorationVisible.value,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 1000,
            ),
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 1000,
            ),
        ),
    ) {
        Canvas(modifier) {
            drawTrackDecoration(
                trackDecorationColor = Color.White,
            )
        }
    }
}

@Composable
fun Thumb(
    modifier: Modifier,
    thumbTranslation: Dp,
    thumbPadding: Dp,
    thumbColor: Color,
    thumbStrokeColor: Color,
) {
    Canvas(modifier,) {
        drawThumb(
            trackWidth = trackWidth,
            thumbTranslation = thumbTranslation,
            thumbPadding = thumbPadding,
            thumbColor = thumbColor,
            thumbStrokeColor = thumbStrokeColor,
        )
    }
}

@Composable
fun ThumbDecoration(
    isThumbDecorationVisible: MutableState<Boolean>,
    modifier: Modifier,
    thumbDecorationColor: Color,
    thumbTranslation: Dp,
) {
    AnimatedVisibility(
        visible = isThumbDecorationVisible.value,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 500,
                delayMillis = 1000,
            ),
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 500,
                delayMillis = 1000,
            ),
        ),
    ) {
        Canvas(modifier,) {
            drawThumbDecoration(
                thumbDecorationColor = thumbDecorationColor,
                thumbTranslation = thumbTranslation,
            )
        }
    }
}

private fun DrawScope.drawThumb(
    trackWidth: Dp,
    thumbTranslation: Dp,
    thumbPadding: Dp,
    thumbColor: Color,
    thumbStrokeColor: Color,
) = translate(
    left = thumbTranslation.toPx(),
) {
    val thumbRadius = 45.dp
    val thumbStrokeRadius = 6.dp

    drawCircle(
        color = thumbColor,
        radius = thumbRadius.toPx() - (thumbStrokeRadius / 2).toPx(),
        center = Offset(
            x = (trackWidth - thumbPadding - thumbRadius).toPx(),
            y = center.y,
        ),
    )

    drawCircle(
        color = thumbStrokeColor,
        radius = thumbRadius.toPx(),
        center = Offset(
            x = (trackWidth - thumbPadding - thumbRadius).toPx(),
            y = center.y,
        ),
        style = Stroke(
            width = thumbStrokeRadius.toPx(),
        ),
    )
}

private fun DrawScope.drawTrackDecoration(
    trackDecorationColor: Color,
) {
    drawCircle(
        color = trackDecorationColor,
        radius = 1.5.dp.toPx(),
        center = Offset(
            x = 129.dp.toPx(),
            y = 33.dp.toPx(),
        ),
    )

    drawCircle(
        color = trackDecorationColor,
        radius = 2.dp.toPx(),
        center = Offset(
            x = 138.dp.toPx(),
            y = 69.dp.toPx(),
        ),
    )

    drawCircle(
        color = trackDecorationColor,
        radius = 3.dp.toPx(),
        center = Offset(
            x = 118.dp.toPx(),
            y = 99.dp.toPx(),
        ),
    )

    drawCircle(
        color = trackDecorationColor,
        radius = 3.dp.toPx(),
        center = Offset(
            x = 165.dp.toPx(),
            y = 50.dp.toPx(),
        ),
    )

    drawCircle(
        color = trackDecorationColor,
        radius = 3.dp.toPx(),
        center = Offset(
            x = 162.dp.toPx(),
            y = 90.dp.toPx(),
        ),
    )

    drawCircle(
        color = trackDecorationColor,
        radius = 2.dp.toPx(),
        center = Offset(
            x = 184.dp.toPx(),
            y = 75.dp.toPx(),
        ),
    )

    drawCircle(
        color = trackDecorationColor,
        radius = 3.dp.toPx(),
        center = Offset(
            x = 207.dp.toPx(),
            y = 37.dp.toPx(),
        ),
    )

    drawCircle(
        color = trackDecorationColor,
        radius = 2.dp.toPx(),
        center = Offset(
            x = 205.dp.toPx(),
            y = 93.dp.toPx(),
        ),
    )

    drawCircle(
        color = trackDecorationColor,
        radius = 2.dp.toPx(),
        center = Offset(
            x = 219.dp.toPx(),
            y = 66.dp.toPx(),
        ),
    )
}

private fun DrawScope.drawThumbDecoration(
    thumbDecorationColor: Color,
    thumbTranslation: Dp,
) = translate(
    left = thumbTranslation.toPx(),
) {
    drawCircle(
        color = thumbDecorationColor,
        radius = 7.dp.toPx(),
        center = Offset(
            x = 150.dp.toPx(),
            y = 27.dp.toPx(),
        ),
    )

    drawCircle(
        color = thumbDecorationColor,
        radius = 10.dp.toPx(),
        center = Offset(
            x = 195.dp.toPx(),
            y = 24.dp.toPx(),
        ),
        style = Stroke(
            width = 6.dp.toPx(),
        ),
    )

    drawCircle(
        color = thumbDecorationColor,
        radius = 6.dp.toPx(),
        center = Offset(
            x = 191.dp.toPx(),
            y = 84.dp.toPx(),
        ),
        style = Stroke(
            width = 6.dp.toPx(),
        ),
    )
}

@Preview
@Composable
fun DayAndNightSwitchPreview() {
    DrawingSwitchTheme {
        DayAndNightSwitch()
    }
}