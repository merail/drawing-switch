package me.rail.drawing_switch.day_and_night_switch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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