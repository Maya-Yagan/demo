package com.maya2002yagan.weatherapp.view.detail

import android.os.Bundle
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
import com.maya2002yagan.weatherapp.viewmodel.MainViewModel

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding : FragmentDetailBinding
    private val viewModel : MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        viewModel.findByID(args.WeatherDetails.id)
        initUI()
        return binding.root
    }

    private fun initUI(){
        viewModel.weather.observe(viewLifecycleOwner){ weather ->
            weather?.daily?.let {
                with(binding) {
                    tvMaxTemperatureValue.text = it.temperature_2m_max.toString()
                    tvMinTemperatureValue.text = it.temperature_2m_min.toString()
                    tvRainSumValue.text = it.rain_sum.toString()
                    tvMaxWindSpeedValue.text = it.wind_speed_10m_max.toString()
                    tvUVIndexValue.text = it.uv_index_clear_sky_max.toString()
                    tvMaxPrecipitationProbValue.text = it.precipitation_probability_max.toString()
                }
            }
        }
    }
}