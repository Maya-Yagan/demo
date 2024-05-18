package com.maya2002yagan.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maya2002yagan.weatherapp.databinding.ItemWeatherBinding
import com.maya2002yagan.weatherapp.model.WeatherResponse

class WeatherAdapter(private val list : MutableList<WeatherResponse>) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    inner class WeatherViewHolder(private val binding : ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather : WeatherResponse, position : Int){
            binding.tvMaxTemperature.text = weather.daily.temperature_2m_max[position].toString()
            binding.tvMinTemperature.text = weather.daily.temperature_2m_min[position].toString()
            binding.tvDate.text = weather.daily.time[position]
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
        list.addAll(listOf(newList))
        notifyDataSetChanged()
    }
}