package me.rail.drawing_switch.day_and_night_switch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DisabledTrackDecoration(
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
            drawCircle(
                color = Color.White,
                radius = 1.5.dp.toPx(),
                center = Offset(
                    x = 129.dp.toPx(),
                    y = 33.dp.toPx(),
                ),
            )

            drawCircle(
                color = Color.White,
                radius = 2.dp.toPx(),
                center = Offset(
                    x = 138.dp.toPx(),
                    y = 69.dp.toPx(),
                ),
            )

            drawCircle(
                color = Color.White,
                radius = 3.dp.toPx(),
                center = Offset(
                    x = 118.dp.toPx(),
                    y = 99.dp.toPx(),
                ),
            )

            drawCircle(
                color = Color.White,
                radius = 3.dp.toPx(),
                center = Offset(
                    x = 165.dp.toPx(),
                    y = 50.dp.toPx(),
                ),
            )

            drawCircle(
                color = Color.White,
                radius = 3.dp.toPx(),
                center = Offset(
                    x = 162.dp.toPx(),
                    y = 90.dp.toPx(),
                ),
            )

            drawCircle(
                color = Color.White,
                radius = 2.dp.toPx(),
                center = Offset(
                    x = 184.dp.toPx(),
                    y = 75.dp.toPx(),
                ),
            )

            drawCircle(
                color = Color.White,
                radius = 3.dp.toPx(),
                center = Offset(
                    x = 207.dp.toPx(),
                    y = 37.dp.toPx(),
                ),
            )

            drawCircle(
                color = Color.White,
                radius = 2.dp.toPx(),
                center = Offset(
                    x = 205.dp.toPx(),
                    y = 93.dp.toPx(),
                ),
            )

            drawCircle(
                color = Color.White,
                radius = 2.dp.toPx(),
                center = Offset(
                    x = 219.dp.toPx(),
                    y = 66.dp.toPx(),
                ),
            )
        }
    }
}

@Composable
fun EnabledTrackDecoration(
    isTrackDecorationVisible: MutableState<Boolean>,
    trackWidth: Dp,
    trackHeight: Dp,
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
        val strokeColor = Color(
            red = 204,
            green = 204,
            blue = 204,
        )
        val color = Color(
            red = 241,
            green = 241,
            blue = 241,
        )
        Canvas(modifier) {
            val width = trackWidth.toPx()
            val height = trackHeight.toPx()
            val path = Path().apply {
                moveTo(width.times(0.065f), height.times(0.65f))
                cubicTo(
                    x1 = width.times(0.065f),
                    y1 = height.times(0.55f),
                    x2 = width.times(0.12f),
                    y2 = height.times(0.54f),
                    x3 = width.times(0.14f),
                    y3 = height.times(0.63f),
                )
                cubicTo(
                    x1 = width.times(0.19f),
                    y1 = height.times(0.54f),
                    x2 = width.times(0.22f),
                    y2 = height.times(0.6f),
                    x3 = width.times(0.22f),
                    y3 = height.times(0.69f),
                )
                cubicTo(
                    x1 = width.times(0.28f),
                    y1 = height.times(0.72f),
                    x2 = width.times(0.25f),
                    y2 = height.times(0.88f),
                    x3 = width.times(0.19f),
                    y3 = height.times(0.88f),
                )
                lineTo(
                    x = width.times(0.11f),
                    y = height.times(0.88f),
                )
                cubicTo(
                    x1 = width.times(0.01f),
                    y1 = height.times(0.87f),
                    x2 = width.times(0.03f),
                    y2 = height.times(0.69f),
                    x3 = width.times(0.07f),
                    y3 = height.times(0.69f),
                )
                close()
            }
            drawPath(
                path = path,
                color = color,
            )
            drawPath(
                path = path,
                color = strokeColor,
                style = Stroke(
                    width = 2.dp.toPx(),
                ),
            )
        }
    }
}

@Composable
@Preview
fun EnabledTrackDecorationPreview() {
    EnabledTrackDecoration(
        isTrackDecorationVisible = remember {
            mutableStateOf(true)
        },
        trackWidth = 234.dp,
        trackHeight = 109.dp,
        modifier = Modifier
            .size(
                width = 234.dp,
                height = 109.dp,
            ),
    )
}