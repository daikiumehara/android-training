package jp.co.yumemi.droidtraining

enum class WeatherCase(val value: Int) {
    SUNNY(value = R.drawable.baseline_wb_sunny_24),
    CLOUDY(value = R.drawable.baseline_cloud_24),
    RAINY(value = R.drawable.baseline_umbrella_24),
    SNOW(value = R.drawable.baseline_ac_unit_24),
}

class WeatherCaseConvertor() {
    companion object {
        fun convert(weatherString: String): WeatherCase {
            return when (weatherString) {
                "Sunny" -> WeatherCase.SUNNY
                "Cloudy" -> WeatherCase.CLOUDY
                "Rainy" -> WeatherCase.RAINY
                "Snow" -> WeatherCase.SNOW
                else -> WeatherCase.SUNNY
            }
        }
    }
}
