package com.maya2002yagan.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.model.WeatherResponse
import com.maya2002yagan.weatherapp.service.WeatherAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel() : ViewModel() {
    private val api = WeatherAPIService()
    val weatherData = MutableLiveData<WeatherResponse>()
    val weatherError = MutableLiveData<Boolean>()
    val weatherLoading = MutableLiveData<Boolean>()

    fun getDataFromAPI(){
        weatherLoading.value = true
        api.getData().enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                weatherData.value = response.body()
                weatherError.value = false
                weatherLoading.value = false
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                weatherLoading.value = false
                weatherError.value = true
                Log.e("RetrofitError", t.message.toString())
            }
        })
    }
}