package com.development.myweather.data.remote

import com.development.myweather.data.response_model.hourly.ForecastHourlyResponseModel
import retrofit2.Response

interface ForecastHourlyRemote {

    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    suspend fun getForecastHourly(lat: String, lon: String): Response<ForecastHourlyResponseModel>
}