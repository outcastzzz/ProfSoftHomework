package com.example.lesson6.domain.entity

data class Weather(
    val weather: List<WeatherMini>,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val name: String
)

data class WeatherMini(
    val description: String
)

data class Main(
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
)

data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)
