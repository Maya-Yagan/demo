package com.maya2002yagan.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maya2002yagan.weatherapp.databinding.ItemWeatherBinding
import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.model.WeatherResponse
import com.maya2002yagan.weatherapp.util.convertToDailyWeather

class WeatherAdapter(private val list : MutableList<DailyWeather>, private val onClick : (position : Int) -> Unit) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    inner class WeatherViewHolder(private val binding : ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dailyWeather: DailyWeather, position : Int){
            binding.tvMaxTemperature.text = dailyWeather.temperature_2m_max[position].toString() + "°C"
            binding.tvMinTemperature.text = dailyWeather.temperature_2m_min[position].toString() + "°C"
            binding.tvDate.text = dailyWeather.time[position]
            binding.cardView.setOnClickListener {
                onClick(position)
            }
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

    fun updateList(newList: WeatherResponse){
        list.clear()
        val dailyWeather = convertToDailyWeather(newList)
        list.addAll(dailyWeather)
        notifyDataSetChanged()
    }
}