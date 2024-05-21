package com.maya2002yagan.weatherapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.maya2002yagan.weatherapp.database.WeatherDao
import com.maya2002yagan.weatherapp.database.WeatherDatabase
import com.maya2002yagan.weatherapp.model.CurrentWeather
import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.model.WeatherResponse
import com.maya2002yagan.weatherapp.model.WeatherUnits
import com.maya2002yagan.weatherapp.service.WeatherAPIService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application : Application) : AndroidViewModel(application) {
    private val api = WeatherAPIService()
    val weatherData = MutableLiveData<WeatherResponse>()
    val weatherError = MutableLiveData<Boolean>()
    val weatherLoading = MutableLiveData<Boolean>()
    val weather = MutableLiveData<WeatherResponse>()
    private var weatherDatabase : WeatherDatabase? = null
    private var weatherDao : WeatherDao? = null

    init {
        weatherDatabase = WeatherDatabase.getInstance(application)
        weatherDao = weatherDatabase?.weatherDao()
    }

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

    fun insertAll(list : List<DailyWeather>) = viewModelScope.launch {
        weatherDao?.insertAll(list)
    }

    fun findByID(id: Int) = viewModelScope.launch {
        weather.value?.daily = weatherDao?.findByID(id)!!
    }
}