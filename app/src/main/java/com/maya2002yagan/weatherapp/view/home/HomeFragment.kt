package com.maya2002yagan.weatherapp.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.maya2002yagan.weatherapp.R
import com.maya2002yagan.weatherapp.adapter.WeatherAdapter
import com.maya2002yagan.weatherapp.databinding.FragmentHomeBinding
import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.util.ApplicationViewModelFactory
import com.maya2002yagan.weatherapp.util.convertToDailyWeather
import com.maya2002yagan.weatherapp.util.getWeatherIcon
import com.maya2002yagan.weatherapp.viewmodel.MainViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.rvWeatherRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getDataFromAPI()
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.weatherData.observe(viewLifecycleOwner) { list ->
            val adapter = WeatherAdapter(requireContext() , mutableListOf()) { position ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(position)
                findNavController().navigate(action)
            }
            binding.rvWeatherRecyclerView.adapter = adapter
            list?.let {
                adapter.updateList(it)
                binding.ivWeatherImage.setImageResource(getWeatherIcon(it.current.weather_code, it.current.is_day, requireContext()))
                binding.tvTemperature.text = "${it.current.temperature_2m}${it.current_units.temperature_2m}"
                binding.tvTimeZone.text = it.timezone
                binding.tvCloudCover.text = "${it.current.cloud_cover}${it.current_units.cloud_cover}"
                binding.tvApparentTemperature.text = "${it.current.apparent_temperature}${it.current_units.apparent_temperature}"
                binding.tvHumidity.text = "${it.current.relative_humidity_2m}${it.current_units.relative_humidity_2m}"
                binding.tvWindDirection.text = "${it.current.wind_direction_10m}${it.current_units.wind_direction_10m}"
                binding.tvWindSpeed.text = "${it.current.wind_speed_10m}${it.current_units.wind_speed_10m}"
            }
            viewModel.insertAll(listOf(list.daily))
        }

        viewModel.weatherLoading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.pbLoading.visibility = View.VISIBLE
                binding.ivWeatherImage.visibility = View.GONE
                binding.tvTimeZone.visibility = View.GONE
                binding.cvWeatherDetailsCard.visibility = View.GONE
                binding.tvWeatherTodayText.visibility = View.GONE
            }
            else {
                binding.pbLoading.visibility = View.GONE
                binding.ivWeatherImage.visibility = View.VISIBLE
                binding.tvTimeZone.visibility = View.VISIBLE
                binding.cvWeatherDetailsCard.visibility = View.VISIBLE
                binding.tvWeatherTodayText.visibility = View.VISIBLE
            }
        }

        viewModel.weatherError.observe(viewLifecycleOwner) { error ->
            if (error)
                binding.tvError.visibility = View.VISIBLE
            else
                binding.tvError.visibility = View.GONE
        }
    }
}
