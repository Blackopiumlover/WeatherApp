package com.yongjia.weatherapp.data.remote

interface WeatherApi {

    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    )
}