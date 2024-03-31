package com.development.myweather.data

import com.development.myweather.data.response_model.current.CurrentWeatherResponseModel
import com.development.myweather.data.response_model.hourly.ForecastHourlyResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Response<CurrentWeatherResponseModel>

    @GET("/data/2.5/forecast")
    suspend fun getForecastHourly(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Response<ForecastHourlyResponseModel>

}