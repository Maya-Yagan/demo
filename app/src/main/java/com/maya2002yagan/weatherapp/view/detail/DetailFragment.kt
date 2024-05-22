package com.maya2002yagan.weatherapp.view.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.maya2002yagan.weatherapp.R
import com.maya2002yagan.weatherapp.databinding.FragmentDetailBinding
import com.maya2002yagan.weatherapp.util.ApplicationViewModelFactory
import com.maya2002yagan.weatherapp.util.getWeatherIcon
import com.maya2002yagan.weatherapp.viewmodel.MainViewModel

class DetailFragment : Fragment() {
    val args: DetailFragmentArgs by navArgs()
    private lateinit var binding : FragmentDetailBinding
    private val viewModel : MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        viewModel.getDataFromAPI()
        initUI()
        return binding.root
    }

    private fun initUI(){
        viewModel.weatherData.observe(viewLifecycleOwner){ weather ->
            weather?.daily?.let {
                with(binding){
                    tvMaxTemperatureValue.text = it.temperature_2m_max[args.WeatherDetails].toString() + weather.daily_units.temperature_2m_max
                    tvMinTemperatureValue.text = it.temperature_2m_min[args.WeatherDetails].toString() + weather.daily_units.temperature_2m_min
                    tvRainSumValue.text = it.rain_sum[args.WeatherDetails].toString() + weather.daily_units.rain_sum
                    tvMaxWindSpeedValue.text = it.wind_speed_10m_max[args.WeatherDetails].toString() + weather.daily_units.wind_speed_10m_max
                    tvUVIndexValue.text = it.uv_index_clear_sky_max[args.WeatherDetails].toString()
                    tvMaxPrecipitationProbValue.text = it.precipitation_probability_max[args.WeatherDetails].toString() + weather.daily_units.precipitation_probability_max
                    ivWeatherImageDetail.setImageResource(getWeatherIcon(it.weather_code[args.WeatherDetails], 1, requireContext()))
                }
            }
        }

        viewModel.weatherLoading.observe(viewLifecycleOwner){ loading ->
            if(loading){
                binding.pbLoading.visibility = View.VISIBLE
                binding.ivWeatherImageDetail.visibility = View.GONE
                binding.cvWeatherDetailsCard.visibility = View.GONE
            }
            else{
                binding.pbLoading.visibility = View.GONE
                binding.ivWeatherImageDetail.visibility= View.VISIBLE
                binding.cvWeatherDetailsCard.visibility = View.VISIBLE
            }
        }
    }
}