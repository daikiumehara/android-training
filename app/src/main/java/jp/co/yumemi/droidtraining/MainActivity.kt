package jp.co.yumemi.droidtraining

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
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
import androidx.compose.ui.text.font.FontWeight
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
    var weatherState: WeatherState by remember { mutableStateOf(WeatherState(null, null)) }

    fun reloadAction(): Unit {
        val (info, error) = weatherClient.fetchWeather()
        val state = WeatherState(info ?: weatherState.info, error)
        weatherState = state
    }

    fun closeAction(): Unit {
        weatherState = WeatherState(weatherState.info, null)
    }

    ConstraintLayout(
        constraintSet = mainViewConstraints(),
        modifier = Modifier.fillMaxSize()
    ) {
        WeatherInfoView(
            weatherInfo = weatherState.info,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .layoutId("weatherInfoView")
        )

        ActionButtons(
            reloadAction = ::reloadAction,
            nextAction = {}
        )
    }

    when {
        weatherState.error != null -> {
            ErrorDialog(
                description = weatherState.error!!,
                reloadAction = ::reloadAction,
                closeAction = ::closeAction
            )
        }
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

@Composable
fun ErrorDialog(
    description: String,
    reloadAction: () -> Unit,
    closeAction: () -> Unit
) {
    AlertDialog(
        title = {
            Text(
                text = "エラー",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        text = { Text(description) },
        onDismissRequest = {
            closeAction()
        },
        buttons = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 16.dp),
            ) {
                Button(onClick = closeAction) {
                    Text(text = "CLOSE")
                }

                Spacer(Modifier.width(16.dp))

                Button(onClick = reloadAction) {
                    Text("RELOAD")
                }
            }
        }
    )
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
