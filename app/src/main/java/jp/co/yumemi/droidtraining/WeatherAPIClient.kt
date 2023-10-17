package jp.co.yumemi.droidtraining

import android.content.Context
import jp.co.yumemi.api.YumemiWeather

interface WeatherAPIClient {
    fun fetchWeather(): WeatherCase
}

class WeatherAPIClientImpl(context: Context): WeatherAPIClient {
    private val api: YumemiWeather = YumemiWeather(context = context)
    override  fun fetchWeather(): WeatherCase {
        return WeatherCaseConvertor.convert(api.fetchSimpleWeather())
    }
}
