package com.example.lesson6.domain.repository

import com.example.lesson6.domain.entity.Weather

interface MainRepository {

    suspend fun getCurrentWeather(lat: Double, long: Double): Weather

}