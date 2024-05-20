package com.maya2002yagan.weatherapp.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.maya2002yagan.weatherapp.R
import com.maya2002yagan.weatherapp.databinding.FragmentDetailBinding
import com.maya2002yagan.weatherapp.viewmodel.MainViewModel

class DetailFragment : Fragment() {
    val args : DetailFragmentArgs by navArgs()
    private lateinit var binding : FragmentDetailBinding
    private lateinit var viewModel : MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
}