package com.development.myweather.persentation.home_screen.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.development.myweather.data.remote.ForecastHourlyRemote
import com.development.myweather.data.response_model.hourly.ForecastHourlyResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class ForecastHourlyViewModel @Inject constructor(private val forecastHourlyRemote: ForecastHourlyRemote) : ViewModel() {
//
//    // setter
//    private val _forecastHourly = MutableLiveData<ForecastHourlyResponseModel>()
//    private val _forecastHourlyError = MutableLiveData<String>()
//
//    // getter
//    val forecastHourly: LiveData<ForecastHourlyResponseModel> get() = _forecastHourly
//    val forecastHourlyError: LiveData<String> get() = _forecastHourlyError
//
//    fun getForecastHourly(lat: String, lon: String) = viewModelScope.launch(Dispatchers.IO) {
//        forecastHourlyRemote.getForecastHourly(lat, lon).let{
//            if (it.isSuccessful) {
//                _forecastHourly.postValue(it.body())
//            } else {
//                _forecastHourlyError.postValue(it.message())
//            }
//        }
//    }
