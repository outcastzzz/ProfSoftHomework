package com.example.lesson6.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson6.domain.entity.Weather
import com.example.lesson6.domain.useCase.GetCurrentWeatherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
): ViewModel() {

    private val _screenState = MutableLiveData<MainScreenState>()
    val screenState: LiveData<MainScreenState> = _screenState

    init {
        _screenState.value = MainScreenState.Loading
        getCurrentWeather(SARATOV_LAT, SARATOV_LON)
    }

    private fun getCurrentWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            try {
                val weather = getCurrentWeatherUseCase(lat, long)
                _screenState.value = MainScreenState.SuccessfulLoaded(weather)
            } catch (e: Exception) {
                _screenState.value = MainScreenState.Error
            }

        }
    }

    companion object {

        private const val SARATOV_LAT = 51.533
        private const val SARATOV_LON = 40.017

    }

}