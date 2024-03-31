package com.development.myweather.data.remote

import com.development.myweather.data.response_model.current.CurrentWeatherResponseModel
import retrofit2.Response

interface CurrentWeatherRemote {

    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    suspend fun getCurrentWeather(lat: String, lon: String): Response<CurrentWeatherResponseModel>
}