package com.yongjia.weatherapp.domain.weather

import java.time.LocalDateTime

/**
 * 某一个时刻的天气数据
 */
data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
