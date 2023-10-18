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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun WeatherInfoView(
    weatherInfo: WeatherInfo?,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(weatherInfo?.weatherCase?.value ?: R.drawable.baseline_question_mark_24),
            contentDescription = null,
            Modifier
                .aspectRatio(1f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Text(
                weatherInfo?.maxTemp?.toString() ?: "--",
                color = Color.Red,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                weatherInfo?.minTemp?.toString() ?: "--",
                color = Color.Blue,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun WeatherInfoView_Sunny_Preview() {
    WeatherInfoView(WeatherInfo(weatherCase = WeatherCase.SUNNY, maxTemp = 10, minTemp = 0))
}

@Preview
@Composable
fun WeatherInfoView_Cloudy_Preview() {
    WeatherInfoView(WeatherInfo(weatherCase = WeatherCase.CLOUDY, maxTemp = 10, minTemp = 0))
}

@Preview
@Composable
fun WeatherInfoView_Rainy_Preview() {
    WeatherInfoView(WeatherInfo(weatherCase = WeatherCase.RAINY, maxTemp = 10, minTemp = 0))
}

@Preview
@Composable
fun WeatherInfoView_Snow_Preview() {
    WeatherInfoView(WeatherInfo(weatherCase = WeatherCase.SNOW, maxTemp = 10, minTemp = 0))
}

@Preview
@Composable
fun WeatherInfoView_None_Preview() {
    WeatherInfoView(weatherInfo = null)
}
