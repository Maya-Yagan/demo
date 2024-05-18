package com.maya2002yagan.weatherapp.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maya2002yagan.weatherapp.R
import com.maya2002yagan.weatherapp.adapter.WeatherAdapter
import com.maya2002yagan.weatherapp.databinding.FragmentHomeBinding
import com.maya2002yagan.weatherapp.viewmodel.MainViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel : MainViewModel
    private lateinit var binding : FragmentHomeBinding
    private var adapter = WeatherAdapter(mutableListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.rvWeatherRecyclerView.adapter = adapter
        binding.rvWeatherRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getDataFromAPI()
        setObservers()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setObservers(){
        viewModel.weatherData.observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter.updateList(it)
            }
        }

        viewModel.weatherLoading.observe(viewLifecycleOwner){ loading ->
            if(loading)
                binding.pbLoading.visibility = View.VISIBLE
            else
                binding.pbLoading.visibility = View.GONE
        }

        viewModel.weatherError.observe(viewLifecycleOwner) {error ->
            if(error)
                binding.tvError.visibility = View.VISIBLE
            else
                binding.tvError.visibility = View.GONE
        }
    }
}