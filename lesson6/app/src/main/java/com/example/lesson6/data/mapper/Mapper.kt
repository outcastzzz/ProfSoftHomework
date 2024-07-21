package com.example.lesson6.data.mapper

import com.example.lesson6.data.network.models.MainDto
import com.example.lesson6.data.network.models.WeatherDto
import com.example.lesson6.data.network.models.WeatherMiniDto
import com.example.lesson6.data.network.models.WindDto
import com.example.lesson6.domain.entity.Main
import com.example.lesson6.domain.entity.Weather
import com.example.lesson6.domain.entity.WeatherMini
import com.example.lesson6.domain.entity.Wind

fun weatherDtoToEntity(dto: WeatherDto): Weather {
    return Weather(
        weather = dto.weather.map { it.toWeatherMini() },
        main = dto.main.toMain(),
        visibility = dto.visibility,
        wind = dto.wind.toWind(),
        name = dto.name
    )
}

private fun WeatherMiniDto.toWeatherMini(): WeatherMini {
    return WeatherMini(
        description = this.description
    )
}

private fun MainDto.toMain(): Main {
    return Main(
        temp = this.temp,
        feelsLike = this.feelsLike,
        tempMin = this.tempMin,
        tempMax = this.tempMax,
        pressure = this.pressure,
        humidity = this.humidity
    )
}

private fun WindDto.toWind(): Wind {
    return Wind(
        speed = this.speed,
        deg = this.deg,
        gust = this.gust
    )
}
