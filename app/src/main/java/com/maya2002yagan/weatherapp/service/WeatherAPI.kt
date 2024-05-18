package com.maya2002yagan.weatherapp.service

import com.maya2002yagan.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET

//https://api.open-meteo.com/
interface WeatherAPI {
    @GET("v1/forecast?latitude=40.660431&longitude=29.272478&current=temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,cloud_cover,wind_speed_10m,wind_direction_10m&daily=weather_code,temperature_2m_max,temperature_2m_min&timezone=auto")
    fun getWeather() : Call<WeatherResponse>
}