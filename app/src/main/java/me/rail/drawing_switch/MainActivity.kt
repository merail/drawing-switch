package me.rail.drawing_switch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        ColoredTrackSwitch()

        MathematicalSwitch()
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