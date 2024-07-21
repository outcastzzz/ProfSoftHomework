package com.example.lesson6.presentation

import com.example.lesson6.domain.entity.Weather

sealed class MainScreenState {

    data object Error: MainScreenState()

    data object Loading: MainScreenState()

    data class SuccessfulLoaded(val weather: Weather): MainScreenState()

}