package com.development.myweather.persentation.home_screen.view

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
import com.development.myweather.persentation.home_screen.view_model.ForecastHourlyViewModel
import com.development.myweather.utils.HorizontalItemDecoration

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CurrentWeatherFragment : BaseFragment<FragmentCurrentWeatherBinding>() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModelCurrentWeather: CurrentWeatherViewModel by viewModels()
    private val viewModelForecastHourly: ForecastHourlyViewModel by viewModels()

    private lateinit var forecastHourlyAdapter: ForecastHourlyAdapter

    private val horizontalItemDecoration by lazy {
        HorizontalItemDecoration(
            resources.getDimensionPixelOffset(R.dimen.spacing16),
            true
        )
    }
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCurrentWeatherBinding {
        return FragmentCurrentWeatherBinding.inflate(inflater, container, false)
    }

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

        // Get last location
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations, this can be null.
                location?.let {
                    val latitude = it.latitude.toString()
                    val longitude = it.longitude.toString()
                    // use latitude and longitude in your API calls
                    viewModelCurrentWeather.getCurrentWeather(latitude, longitude)
                    viewModelForecastHourly.getForecastHourly(latitude, longitude)
                }
            }

        observeViewModel()
    }


    private fun observeViewModel() {
        viewModelCurrentWeather.currentWeather.observe(viewLifecycleOwner) {it ->
            setUpCurrentWeatherView(it)
        }

        viewModelForecastHourly.forecastHourly.observe(viewLifecycleOwner) {it ->
            setUpViewForecastHourly(it.forecastDataList.subList(0, 8)) // sublist untuk slice list berdasarkan index
        }
    }

    private fun setUpCurrentWeatherView(data: CurrentWeatherResponseModel) {
        binding.currentWeather.currentTemp.text = data.main.temp.toString()
        binding.currentWeather.currentCity.text = data.name.toString()
        binding.currentWeather.currentPressure.text = data.main.pressure.toString()
        binding.currentWeather.currentWind.text = data.wind.speed.toString()
        binding.currentWeather.currentHumidity.text = data.main.humidity.toString()
        binding.currentWeather.currentFeelsLike.text = data.main.feelsLike.toString()

        // set image dengan string resource template
        // val imageName = "weather${data.weather[0].icon}"
        // val resourceId = resources.getIdentifier(imageName, "drawable", "com.dira.weatherapp")
        // binding.currentWeather.currentImageWeather.setImageResource(resourceId)

        // load image dengan glide
        val imageCode = data.weather[0].icon
        Glide.with(this)
            .load("https://openweathermap.org/img/wn/$imageCode@2x.png?")
            .override(160, 80)
            .centerCrop()
            .into(binding.currentWeather.currentImageWeather)
    }

    private fun setUpViewForecastHourly(data: List<ForecastDataHourly>) {
        forecastHourlyAdapter = ForecastHourlyAdapter(data)
        binding.forecastHourly.recycleForecastHourly.adapter = forecastHourlyAdapter

        // untuk setting orientasi recycle viewnya, bisa juga secara manual edit orientation di component recycleView
        binding.forecastHourly.recycleForecastHourly.layoutManager = LinearLayoutManager(
            binding.root.context, LinearLayoutManager.HORIZONTAL, false
        )
        // menambahkan styling untuk setiap item recycleView
        binding.forecastHourly.recycleForecastHourly.apply {
            if (itemDecorationCount <= 0) {
                addItemDecoration(horizontalItemDecoration)
            }
        }
    }
}