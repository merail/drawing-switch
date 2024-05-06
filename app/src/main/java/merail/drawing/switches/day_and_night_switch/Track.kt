package merail.drawing.switches.day_and_night_switch

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp

@Composable
fun Track(
    modifier: Modifier,
    trackWidth: Dp,
    trackHeight: Dp,
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