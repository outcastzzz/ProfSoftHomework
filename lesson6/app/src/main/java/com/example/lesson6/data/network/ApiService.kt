package com.example.lesson6.data.network

import com.example.lesson6.BuildConfig
import com.example.lesson6.data.network.models.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = BuildConfig.KEY
    ): WeatherDto

}