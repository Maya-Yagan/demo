package com.maya2002yagan.weatherapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maya2002yagan.weatherapp.model.CurrentWeather
import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.model.WeatherResponse
import com.maya2002yagan.weatherapp.model.WeatherUnits

class Converter {

    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromDoubleList(value: String?): List<Double>? {
        val listType = object : TypeToken<List<Double>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromDoubleList(list: List<Double>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromIntList(value: String?): List<Int>? {
        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromIntList(list: List<Int>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromWeatherUnits(weatherUnits: WeatherUnits?): String? {
        return gson.toJson(weatherUnits)
    }

    @TypeConverter
    fun toWeatherUnits(weatherUnitsString: String?): WeatherUnits? {
        return gson.fromJson(weatherUnitsString, WeatherUnits::class.java)
    }

    @TypeConverter
    fun fromCurrentWeather(currentWeather: CurrentWeather?): String? {
        return gson.toJson(currentWeather)
    }

    @TypeConverter
    fun toCurrentWeather(currentWeatherString: String?): CurrentWeather? {
        return gson.fromJson(currentWeatherString, CurrentWeather::class.java)
    }

    @TypeConverter
    fun fromDailyWeather(dailyWeather: DailyWeather?): String? {
        return gson.toJson(dailyWeather)
    }

    @TypeConverter
    fun toDailyWeather(dailyWeatherString: String?): DailyWeather? {
        return gson.fromJson(dailyWeatherString, DailyWeather::class.java)
    }

    @TypeConverter
    fun fromWeatherResponse(weatherResponse: WeatherResponse?): String? {
        return gson.toJson(weatherResponse)
    }

    @TypeConverter
    fun toWeatherResponse(weatherResponseString: String?): WeatherResponse? {
        return gson.fromJson(weatherResponseString, WeatherResponse::class.java)
    }
}

