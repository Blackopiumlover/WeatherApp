package com.yongjia.weatherapp.domain.repository

import com.yongjia.weatherapp.domain.util.Resource
import com.yongjia.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherInfo>
}