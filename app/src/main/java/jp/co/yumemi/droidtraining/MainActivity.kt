package jp.co.yumemi.droidtraining

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    val weatherClient = WeatherAPIClientImpl(context = LocalContext.current)
    var weather: WeatherCase by remember { mutableStateOf(WeatherCase.SUNNY) }
        ConstraintLayout(
            constraintSet = mainViewConstraints(),
            modifier = Modifier.fillMaxSize()
        ) {
            WeatherInfoView(
                weather = weather,
                maxTemp = 10,
                minTemp = 5,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .layoutId("weatherInfoView")
            )

            ActionButtons(
                reloadAction = {
                    weather = weatherClient.fetchWeather()
                },
                nextAction = {}
            )
        }
}



@Composable
fun ActionButtons(reloadAction: () -> Unit, nextAction: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .layoutId("actionButtons")
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)

        ) {
            Button(
                onClick = reloadAction,
            ) {
                Text(
                    "RELOAD",
                    fontSize = 13.sp
                )
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)
        ) {
            Button(
                onClick = nextAction,
            ) {
                Text(
                    "NEXT"
                )
            }
        }
    }
}


fun mainViewConstraints(): ConstraintSet {
    return ConstraintSet {
        val weatherView = createRefFor("weatherInfoView")
        val actionButtons = createRefFor("actionButtons")

        constrain(weatherView) {
            centerHorizontallyTo(parent)
            centerVerticallyTo(parent)
        }
        constrain(actionButtons) {
            top.linkTo(weatherView.bottom, margin = 80.dp)
            centerHorizontallyTo(weatherView)
        }
    }
}


@Preview
@Composable
fun MainContent_Preview() {
    MainContent()
}
