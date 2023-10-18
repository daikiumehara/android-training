package jp.co.yumemi.droidtraining

import android.content.Context
import jp.co.yumemi.api.YumemiWeather

interface WeatherAPIClient {
    fun fetchWeather(): Pair<WeatherInfo?, String?>
}

class WeatherAPIClientImpl(context: Context): WeatherAPIClient {
    private val api: YumemiWeather = YumemiWeather(context = context)
    override  fun fetchWeather(): Pair<WeatherInfo?, String?> {
        return try {
            val weatherCase = WeatherCaseConvertor.convert(api.fetchThrowsWeather())
            WeatherInfo(weatherCase, 10, 0) to null
        } catch(e: Throwable) {
            null to "エラーが発生しました"
        }
    }
}
