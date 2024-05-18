package com.maya2002yagan.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maya2002yagan.weatherapp.databinding.ItemWeatherBinding
import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.model.WeatherResponse

class WeatherAdapter(private val list : MutableList<DailyWeather>) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    inner class WeatherViewHolder(private val binding : ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dailyWeather: DailyWeather, position : Int){
            binding.tvMaxTemperature.text = dailyWeather.temperature_2m_max[position].toString() + "°C"
            binding.tvMinTemperature.text = dailyWeather.temperature_2m_min[position].toString() + "°C"
            binding.tvDate.text = dailyWeather.time[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    private fun convertToDailyWeather(weatherResponse: WeatherResponse): List<DailyWeather> {
        val dailyWeather = mutableListOf<DailyWeather>()
        val daily = weatherResponse.daily
        for (i in daily.time.indices) {
            val entry = DailyWeather(
                time = daily.time,
                weather_code= daily.weather_code,
                temperature_2m_max = daily.temperature_2m_max,
                temperature_2m_min = daily.temperature_2m_min
            )
            dailyWeather.add(entry)
        }
        return dailyWeather
    }

    fun updateList(newList: WeatherResponse){
        list.clear()
        val dailyWeather = convertToDailyWeather(newList)
        list.addAll(dailyWeather)
        notifyDataSetChanged()
    }
}