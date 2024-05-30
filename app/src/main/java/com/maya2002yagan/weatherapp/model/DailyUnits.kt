package com.maya2002yagan.weatherapp.model

/**
 * This class stores the weather units for DailyWeather class
 *
 * @property time The time of the weather data. Format: ISO 8601.
 * @property weatherCode The weather code. Unit: WMO code.
 * @property maxTemperature2m The maximum temperature at 2 meters above ground level. Unit: Celsius (°C).
 * @property minTemperature2m The minimum temperature at 2 meters above ground level. Unit: Celsius (°C).
 * @property maxUvIndexClearSky The maximum UV index under clear sky conditions.
 * @property rainSum The sum of rain. Unit: Millimeters (mm).
 * @property precipitationProbabilityMax The maximum precipitation probability. Unit: Percentage (%).
 * @property maxWindSpeed10m The maximum wind speed at 10 meters above ground level. Unit: Kilometers per hour (km/h).
 */
data class DailyUnits(
    val time: String, // unit: iso8601
    val weather_code: String, // unit: wmo code
    val temperature_2m_max: String, // unit: °C
    val temperature_2m_min: String, // unit: °C
    val uv_index_clear_sky_max: String, // unit: nothing
    val rain_sum: String, // unit: mm
    val precipitation_probability_max: String, // unit: %
    val wind_speed_10m_max: String, // unit: km/h
)
