package merail.drawing.switches.day_and_night_switch

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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
            translate(
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
        }
    }
}