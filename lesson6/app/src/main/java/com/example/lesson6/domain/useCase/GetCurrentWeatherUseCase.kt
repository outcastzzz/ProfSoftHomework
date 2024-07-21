package com.example.lesson6.domain.useCase

import com.example.lesson6.domain.repository.MainRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: MainRepository,
) {

    suspend operator fun invoke(lat: Double, long: Double) = repository
        .getCurrentWeather(lat, long)

}