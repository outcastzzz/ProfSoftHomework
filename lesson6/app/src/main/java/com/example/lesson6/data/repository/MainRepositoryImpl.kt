package com.example.lesson6.data.repository

import com.example.lesson6.data.mapper.weatherDtoToEntity
import com.example.lesson6.data.network.ApiService
import com.example.lesson6.domain.entity.Weather
import com.example.lesson6.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
): MainRepository {

    override suspend fun getCurrentWeather(lat: Double, long: Double): Weather {
        val response = apiService.getCurrentWeather(lat, long)
        return response.let(::weatherDtoToEntity)
    }
}