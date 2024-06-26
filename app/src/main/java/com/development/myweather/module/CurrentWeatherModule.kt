package com.development.myweather.module

import com.development.myweather.data.WeatherService
import com.development.myweather.data.remote.CurrentWeatherRemote
import com.development.myweather.data.remote.CurrentWeatherRemoteImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
// @Module digunakan untuk menandai kelas sebagai modul Dagger
@InstallIn(SingletonComponent::class)
// @InstallIn digunakan untuk menentukan komponen mana yang akan digunakan untuk menginstal modul tersebut
object CurrentWeatherModule {

    @Singleton
    // @Singleton digunakan untuk menandai bahwa instance yang diberikan oleh fungsi yang diberi anotasi ini akan bersifat singleton (hanya ada satu instance dari suatu kelas)
    @Provides
    // @Provides digunakan untuk menandai sebuah fungsi sebagai provider (fungsi yang bertanggung jawab untuk menyediakan instance dari suatu kelas tertentu)
    fun provideCurrentWeatherRemoteDataSource(service: WeatherService) : CurrentWeatherRemote {
        return CurrentWeatherRemoteImpl(service)
    }
}