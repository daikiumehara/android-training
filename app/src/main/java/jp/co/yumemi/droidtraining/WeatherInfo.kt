package jp.co.yumemi.droidtraining

data class WeatherInfo(
    val weatherCase: WeatherCase,
    val maxTemp: Int,
    val minTemp: Int,
) {
}
