package com.maya2002yagan.weatherapp.model

data class CurrentWeather(
    val time: String,
    val temperature_2m: Double,
    val relative_humidity_2m: Int,
    val apparent_temperature: Double,
    val weather_code: Int,
    val cloud_cover: Int,
    val wind_speed_10m: Double,
    val wind_direction_10m: Int
)

data class DailyWeather(
    val time: List<String>,
    val weather_code: List<Int>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>
)

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val current_units: WeatherUnits,
    val current: CurrentWeather,
    val daily_units: WeatherUnits,
    val daily: DailyWeather
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
    val wind_direction_10m: String
)

