package com.maya2002yagan.weatherapp.util

import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.model.WeatherResponse

fun convertToDailyWeather(weatherResponse: WeatherResponse): List<DailyWeather> {
    val dailyWeather = mutableListOf<DailyWeather>()
    val daily = weatherResponse.daily
    for (i in daily.time.indices) {
        val entry = DailyWeather(
            time = daily.time,
            weather_code= daily.weather_code,
            temperature_2m_max = daily.temperature_2m_max,
            temperature_2m_min = daily.temperature_2m_min,
            precipitation_probability_max = daily.precipitation_probability_max,
            uv_index_clear_sky_max = daily.uv_index_clear_sky_max,
            rain_sum = daily.rain_sum,
            wind_speed_10m_max = daily.wind_speed_10m_max
        )
        dailyWeather.add(entry)
    }
    return dailyWeather
}