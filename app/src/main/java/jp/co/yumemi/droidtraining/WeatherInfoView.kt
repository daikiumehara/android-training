package jp.co.yumemi.droidtraining

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WeatherInfoView(
    weather: WeatherCase,
    maxTemp: Int,
    minTemp: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(weather.value),
            contentDescription = null,
            Modifier
                .aspectRatio(1f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Text(
                maxTemp.toString(),
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                minTemp.toString(),
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun WeatherInfoView_Sunny_Preview() {
    WeatherInfoView(weather = WeatherCase.SUNNY, maxTemp = 10, minTemp = 0)
}

@Preview
@Composable
fun WeatherInfoView_Cloudy_Preview() {
    WeatherInfoView(weather = WeatherCase.CLOUDY, maxTemp = 10, minTemp = 0)
}

@Preview
@Composable
fun WeatherInfoView_Rainy_Preview() {
    WeatherInfoView(weather = WeatherCase.RAINY, maxTemp = 10, minTemp = 0)
}

@Preview
@Composable
fun WeatherInfoView_Snow_Preview() {
    WeatherInfoView(weather = WeatherCase.SNOW, maxTemp = 10, minTemp = 0)
}
