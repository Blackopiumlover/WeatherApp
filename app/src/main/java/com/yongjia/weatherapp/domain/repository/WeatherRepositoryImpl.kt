package com.yongjia.weatherapp.domain.repository

import com.yongjia.weatherapp.data.mappers.toWeatherInfo
import com.yongjia.weatherapp.data.remote.WeatherApi
import com.yongjia.weatherapp.domain.util.Resource
import com.yongjia.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    latitude = latitude,
                    longitude = longitude
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An Unknown error occurred.")
        }
    }
}