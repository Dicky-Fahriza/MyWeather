package com.development.myweather.persentation.home_screen.view

import com.development.myweather.databinding.FragmentSettingBinding
import com.development.myweather.persentation.home_screen.view_model.SettingViewModel

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.development.myweather.R

import com.development.myweather.utils.BaseFragment
import com.development.myweather.data.response_model.current.CurrentWeatherResponseModel
import com.development.myweather.data.response_model.hourly.ForecastDataHourly
import com.development.myweather.databinding.FragmentCurrentWeatherBinding

import com.development.myweather.persentation.home_screen.adapter.ForecastHourlyAdapter
import com.development.myweather.persentation.home_screen.view_model.CurrentWeatherViewModel

import com.development.myweather.utils.HorizontalItemDecoration

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModelSettingWeather: SettingViewModel by viewModels()

    private val horizontalItemDecoration by lazy {
        HorizontalItemDecoration(
            resources.getDimensionPixelOffset(R.dimen.spacing16),
            true
        )
    }
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(inflater, container, false)
    }

//    private fun handleClick() {
//        binding.settingFragment.setOnClickListener {
//            val newFragment = SettingFragment()
//            // Lakukan transaksi fragment untuk menggantikan fragment saat ini dengan fragment baru
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainer, newFragment)
//                .addToBackStack(null) // Tambahkan ke back stack agar dapat kembali ke fragment sebelumnya
//                .commit()
//        }
//    }

    override fun setupView() {
        // Initialize fused location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // Check for location permission
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request location permission if not granted
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1
            )
            return
        }

//        observeViewModel()
    }


//    private fun observeViewModel() {
//        viewModelSettingWeather.settingWeather.observe(viewLifecycleOwner) {it ->
//            setUpCurrentWeatherView(it)
//        }
//
//    }

//    private fun setUpCurrentWeatherView(data: CurrentWeatherResponseModel) {
//        binding.sett.currentTemp.text = data.main.temp.toInt().toString()
//
//    }

}