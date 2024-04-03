package com.development.myweather.data.remote

import com.development.myweather.data.response_model.search.SearchWeatherResponseModel
import retrofit2.Response

interface SearchWeatherRemote {

    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    suspend fun getSearchWeather(city: String): Response<SearchWeatherResponseModel>
}