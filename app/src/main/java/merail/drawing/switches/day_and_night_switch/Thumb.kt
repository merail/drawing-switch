package merail.drawing.switches.day_and_night_switch

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Thumb(
    modifier: Modifier,
    trackWidth: Dp,
    thumbRadius: Dp,
    thumbTranslation: Dp,
    thumbPadding: Dp,
    thumbColor: Color,
    thumbStrokeColor: Color,
) {
    Canvas(modifier,) {
        translate(
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
    }
}