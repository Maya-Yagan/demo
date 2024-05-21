package com.maya2002yagan.weatherapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
@Entity(tableName = "DailyWeather")
data class DailyWeather(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "time")
    val time: List<String>,
    @ColumnInfo(name = "weather_code")
    val weather_code: List<Int>,
    @ColumnInfo(name = "temperature_2m_max")
    val temperature_2m_max: List<Double>,
    @ColumnInfo(name = "temperature_2m_min")
    val temperature_2m_min: List<Double>,
    @ColumnInfo(name = "uv_index_clear_sky_max")
    val uv_index_clear_sky_max: List<Double>,
    @ColumnInfo(name = "rain_sum")
    val rain_sum: List<Double>,
    @ColumnInfo(name = "precipitation_probability_max")
    val precipitation_probability_max: List<Int>,
    @ColumnInfo(name = "wind_speed_10m_max")
    val wind_speed_10m_max: List<Double>
) : Parcelable

@Entity(tableName = "WeatherResponse")
data class WeatherResponse(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
    @ColumnInfo(name = "longitude")
    val longitude: Double,
    @ColumnInfo(name = "current_units")
    val current_units: WeatherUnits,
    @ColumnInfo(name = "current")
    val current: CurrentWeather,
    @ColumnInfo(name = "daily_units")
    val daily_units: WeatherUnits,
    @ColumnInfo(name = "daily")
    var daily: DailyWeather,
    @ColumnInfo(name = "timezone")
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

