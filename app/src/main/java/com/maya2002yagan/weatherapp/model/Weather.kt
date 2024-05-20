package com.maya2002yagan.weatherapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class CurrentWeather(
    val time: String,
    val temperature_2m: Double,
    val relative_humidity_2m: Int,
    val apparent_temperature: Double,
    val weather_code: Int,
    val cloud_cover: Int,
    val wind_speed_10m: Double,
    val wind_direction_10m: Int,
)

@Parcelize
data class DailyWeather(
    val time: List<String>,
    val weather_code: List<Int>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val uv_index_clear_sky_max: List<Double>,
    val rain_sum: List<Double>,
    val precipitation_probability_max: List<Int>,
    val wind_speed_10m_max: List<Double>
) : Parcelable

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val current_units: WeatherUnits,
    val current: CurrentWeather,
    val daily_units: WeatherUnits,
    val daily: DailyWeather,
    val timezone: String
)

data class WeatherUnits(
    val time: String,
    val interval: String,
    val temperature_2m: String,
    val relative_humidity_2m: String,
    val apparent_temperature: String,
    val weather_code: String,
    val cloud_cover: String,
    val wind_speed_10m: String,
    val wind_direction_10m: String,
)

