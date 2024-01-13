package me.rail.drawing_switch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.graphics.Color
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
        ColoredTrackSwitch()
    }
}

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
            trackWidth - (thumbRadius + 9.dp)
        } else {
            thumbRadius + 9.dp
        },
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "ThumbXPositionAnimation",
    )

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
            brush = SolidColor(
                value = trackColor,
            ),
            cornerRadius = CornerRadius(
                x = 60.dp.toPx(),
                y = 60.dp.toPx(),
            ),
        )

        drawCircle(
            brush = SolidColor(
                value = thumbColor,
            ),
            radius = thumbRadius.toPx(),
            center = Offset(
                x = thumbXPosition.toPx(),
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