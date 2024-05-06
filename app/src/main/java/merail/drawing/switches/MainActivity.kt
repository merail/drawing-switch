package merail.drawing.switches

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
import merail.drawing.switches.colored_track_switch.ColoredTrackSwitch
import merail.drawing.switches.day_and_night_switch.DayAndNightSwitch
import merail.drawing.switches.mathematical_switch.MathematicalSwitch
import merail.drawing.switches.ui.theme.DrawingSwitchTheme

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

@Preview(
    showSystemUi = true,
)
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

        DayAndNightSwitch()
    }
}