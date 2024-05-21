package com.maya2002yagan.weatherapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.model.WeatherResponse

@Dao
interface WeatherDao{
    @Query("SELECT * FROM DailyWeather")
    suspend fun getAll(): List<DailyWeather>

    @Query("SELECT * FROM DailyWeather WHERE id = :id")
    suspend fun findByID(id: Int) : DailyWeather

    @Insert
    suspend fun insertAll(list : List<DailyWeather>)
}