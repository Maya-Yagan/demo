package com.maya2002yagan.weatherapp.service

import com.maya2002yagan.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherAPIService {
    private val baseURL = "https://api.open-meteo.com/"
    private val api = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(WeatherAPI::class.java)

    fun getData() : Call<WeatherResponse>{
        return api.getWeather()
    }
}