package me.rail.drawing_switch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.rail.drawing_switch.ui.theme.DrawingSwitchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawingSwitchTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        BackgroundShape()
    }
}

@Composable
fun BackgroundShape() {
    val disabledSwitchBackgroundColor = DrawingSwitchTheme.colors.disabledSwitchBackgroundColor
    val switchColor = DrawingSwitchTheme.colors.switchColor
    val enabledSwitchBackgroundColor = DrawingSwitchTheme.colors.enabledSwitchBackgroundColor

    val isEnabled = remember {
        mutableStateOf(
            value = false,
        )
    }

    val backgroundColor by animateColorAsState(
        targetValue = if (isEnabled.value) enabledSwitchBackgroundColor else disabledSwitchBackgroundColor,
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "BackgroundColorAnimation",
    )

    Canvas(
        modifier = Modifier
            .size(
                width = 234.dp,
                height = 100.dp,
            )
            .clickable {
                isEnabled.value = !isEnabled.value
            },
    ) {
        drawRoundRect(
            brush = SolidColor(
                value = backgroundColor,
            ),
            cornerRadius = CornerRadius(
                x = 60.dp.toPx(),
                y = 60.dp.toPx(),
            ),
        )

        drawCircle(
            brush = SolidColor(
                value = switchColor,
            ),
            radius = 43.dp.toPx(),
            center = Offset(
                x = 53.dp.toPx(),
                y = center.y,
            )
        )
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
fun MainPreview() {
    DrawingSwitchTheme {
        Main()
    }
}