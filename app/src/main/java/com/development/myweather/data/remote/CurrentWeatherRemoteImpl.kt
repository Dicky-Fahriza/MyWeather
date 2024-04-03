package com.development.myweather.data.remote

import com.development.myweather.data.WeatherService
import com.development.myweather.data.response_model.current.CurrentWeatherResponseModel

import retrofit2.Response
import javax.inject.Inject

// @Inject -> digunakan untuk menandai konstruktor atau properti yang akan diinjeksi nilainya oleh Dagger (atau framework dependency injection lainnya) saat membuat instance dari kelas yang bersangkutan
class CurrentWeatherRemoteImpl @Inject constructor(val service: WeatherService) : CurrentWeatherRemote {

    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    override suspend fun getCurrentWeather(lat: String, lon: String): Response<CurrentWeatherResponseModel> {
        return service.getCurrentWeather(lat, lon, "6c95bd14e155cf70a1961ede160d7fd1", "metric")
    }
}