package com.development.myweather.persentation.home_screen.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.development.myweather.data.remote.CurrentWeatherRemote
import com.development.myweather.data.response_model.current.CurrentWeatherResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val currentWeatherRemote: CurrentWeatherRemote) :
    ViewModel() {

    // setter
    private val _settingWeather = MutableLiveData<CurrentWeatherResponseModel>()
    private val _currentWeatherError = MutableLiveData<String>()

    // getter
    val settingWeather: LiveData<CurrentWeatherResponseModel> get() = _settingWeather
    val currentWeatherError: LiveData<String> get() = _currentWeatherError

    fun getCurrentWeather(lat: String, lon: String) = viewModelScope.launch(Dispatchers.IO) {
        currentWeatherRemote.getCurrentWeather(lat, lon).let {
            if (it.isSuccessful) {
                _settingWeather.postValue(it.body())
            } else {
                _currentWeatherError.postValue(it.message())
            }

        }
    }
}