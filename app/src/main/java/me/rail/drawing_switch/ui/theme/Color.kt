package me.rail.drawing_switch.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Colors(
    //Dark theme
    val purple80: Color = Color(0xFFD0BCFF),
    val purpleGrey80: Color = Color(0xFFCCC2DC),
    val pink80: Color = Color(0xFFEFB8C8),

    //Light theme
    val purple40: Color = Color(0xFF6650a4),
    val purpleGrey40: Color = Color(0xFF625b71),
    val pink40: Color = Color(0xFF7D5260),

    val disabledSwitchBackgroundColor: Color = Color(
        red = 234,
        green = 119,
        blue = 92,
    ),
    val switchColor: Color = Color(
        red = 229,
        green = 229,
        blue = 229,
    ),
    val enabledSwitchBackgroundColor: Color = Color(
        red = 57,
        green = 189,
        blue = 59,
    ),
)