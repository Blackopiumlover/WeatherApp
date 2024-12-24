package com.yongjia.weatherapp.domain.weather

data class WeatherInfo(
    /** 每天的 WeatherData （包括时间和天气状态）
     * [0 - Today's WeatherData] -> [0 - [0:00 的 WeatherData, 01:00 的 WeatherData...]]
     * [1 - Tomorrow's WeatherData]
     * ...
     */
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    /**
     * 查询时刻的 WeatherData
     */
    val currentWeatherData: WeatherData?
)