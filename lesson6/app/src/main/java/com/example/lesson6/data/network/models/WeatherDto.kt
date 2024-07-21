package com.example.lesson6.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("weather")
    val weather: List<WeatherMiniDto>,
    @SerialName("main")
    val main: MainDto,
    @SerialName("visibility")
    val visibility: Int,
    @SerialName("wind")
    val wind: WindDto,
    val name: String,
)

@Serializable
data class WeatherMiniDto(
    @SerialName("description")
    val description: String
)

@Serializable
data class MainDto(
    @SerialName("temp")
    val temp: Double,
    @SerialName("feels_like")
    val feelsLike: Double,
    @SerialName("temp_min")
    val tempMin: Double,
    @SerialName("temp_max")
    val tempMax: Double,
    @SerialName("pressure")
    val pressure: Int,
    @SerialName("humidity")
    val humidity: Int,
)

@Serializable
data class WindDto(
    @SerialName("speed")
    val speed: Double,
    @SerialName("deg")
    val deg: Int,
    @SerialName("gust")
    val gust: Double
)
