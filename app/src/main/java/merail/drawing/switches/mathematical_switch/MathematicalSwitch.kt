package merail.drawing.switches.mathematical_switch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import merail.drawing.switches.ui.theme.DrawingSwitchTheme
import kotlin.math.cos
import kotlin.math.sin

@Preview
@Composable
fun MathematicalSwitch() {
    val isEnabled = remember {
        mutableStateOf(
            value = false,
        )
    }

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

    val trackWidth = 234.dp
    val trackHeight = 134.dp
    val thumbRadius = 59.dp
    /**
     * Description is in [ColoredTrackSwitch]
     */
    val thumbPadding = 8.dp
    /**
     * Description is in [ColoredTrackSwitch]
     */
    val thumbXPosition by animateDpAsState(
        targetValue = if (isEnabled.value) {
            trackWidth - (thumbRadius + thumbPadding)
        } else {
            thumbRadius + thumbPadding
        },
        animationSpec = keyframes {
            durationMillis = 1000
            if (isEnabled.value) {
                trackWidth - (thumbRadius + (thumbPadding - thumbPadding / 2)) at 700
                trackWidth - (thumbRadius + (thumbPadding + thumbPadding / 2)) at 850
            } else {
                thumbRadius + (thumbPadding - thumbPadding / 2) at 700
                thumbRadius + (thumbPadding + thumbPadding / 2) at 850
            }
        },
        label = "ThumbXPositionAnimation",
    )

    /**
     * Sine for calculating the y coordinate when rotating one of the "minuses" from horizontal to vertical position and back
     * @see <img width="258" height="285" src="https://raw.githubusercontent.com/merail/android-drawing-switch/main/samples/mathematical_switch_initial_state.png">
     */
    val plusAngleSine by animateFloatAsState(
        targetValue = (
            if (isEnabled.value) {
                sin(Math.toRadians(90.0))
            } else {
                sin(Math.toRadians(180.0))
            }
        ).toFloat(),
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "PlusYPositionAnimation",
    )
    /**
     * Cosine for calculating the y coordinate when rotating one of the "minuses" from horizontal to vertical position and back
     * @see <img width="258" height="285" src="https://raw.githubusercontent.com/merail/android-drawing-switch/main/samples/mathematical_switch_finish_state.png">
     */
    val plusAngleCosine by animateFloatAsState(
        targetValue = (
            if (isEnabled.value) {
                cos(Math.toRadians(90.0))
            } else {
                cos(Math.toRadians(180.0))
            }
        ).toFloat(),
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "PlusYPositionAnimation",
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
                color = color,
                cornerRadius = CornerRadius(trackHeight.toPx()),
            )

            drawThumb(
                color = color,
                thumbRadius = thumbRadius,
                thumbXPosition = thumbXPosition,
                plusAngleSine = plusAngleSine,
                plusAngleCosine = plusAngleCosine,
            )
        }
    }
}

private fun DrawScope.drawThumb(
    color: Color,
    thumbRadius: Dp,
    thumbXPosition: Dp,
    plusAngleSine: Float,
    plusAngleCosine: Float,
) {
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
            x = thumbXPosition.toPx() + plusAngleCosine * (minusWidth / 2).toPx(),
            y = center.y - (minusWidth / 2).toPx() * plusAngleSine,
        ),
        end = Offset(
            x = thumbXPosition.toPx() - plusAngleCosine * (minusWidth / 2).toPx(),
            y = center.y + (minusWidth / 2).toPx() * plusAngleSine,
        ),
        strokeWidth = minusHeight.toPx(),
        cap = StrokeCap.Round,
    )
}