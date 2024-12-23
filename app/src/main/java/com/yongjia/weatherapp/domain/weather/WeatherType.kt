package com.yongjia.weatherapp.domain.weather

import androidx.annotation.DrawableRes
import com.yongjia.weatherapp.R

sealed class WeatherType(
    val weatherDescription: String,
    @DrawableRes val iconResource: Int
) {
    object ClearSky: WeatherType(
        weatherDescription = "Clear Sky",
        iconResource = R.drawable.ic_sunny
    )

    object MainlyClear: WeatherType(
        weatherDescription = "Mainly Clear",
        iconResource = R.drawable.ic_cloudy
    )

    object PartlyCloudy: WeatherType(
        weatherDescription = "Partly Cloudy",
        iconResource = R.drawable.ic_cloudy
    )

    object Overcast: WeatherType(
        weatherDescription = "Overcast",
        iconResource = R.drawable.ic_cloudy
    )

    object Foggy: WeatherType(
        weatherDescription = "Foggy",
        iconResource = R.drawable.ic_very_cloudy
    )

    object DepositingRimeFog: WeatherType(
        weatherDescription = "Depositing rime Fog",
        iconResource = R.drawable.ic_very_cloudy
    )

    object LightDrizzle: WeatherType(
        weatherDescription = "Light Drizzle",
        iconResource = R.drawable.ic_rainshower
    )

    object ModerateDrizzle: WeatherType(
        weatherDescription = "Moderate Drizzle",
        iconResource = R.drawable.ic_rainshower
    )

    object DenseDrizzle: WeatherType(
        weatherDescription = "Dense Drizzle",
        iconResource = R.drawable.ic_rainshower
    )

    object LightFreezingDrizzle: WeatherType(
        weatherDescription = "Slight Freezing Drizzle",
        iconResource = R.drawable.ic_snowyrainy
    )

    object DenseFreezingDrizzle: WeatherType(
        weatherDescription = "Dense Freezing Drizzle",
        iconResource = R.drawable.ic_snowyrainy
    )

    object SlightRain: WeatherType(
        weatherDescription = "Slight Rain",
        iconResource = R.drawable.ic_rainy
    )

    object ModerateRain: WeatherType(
        weatherDescription = "Rainy",
        iconResource = R.drawable.ic_rainy
    )

    object HeavyRain: WeatherType(
        weatherDescription = "Heavy Rain",
        iconResource = R.drawable.ic_rainy
    )

    object HeavyFreezingRain: WeatherType(
        weatherDescription = "Heavy Freezing Rain",
        iconResource = R.drawable.ic_snowyrainy
    )

    object SlightSnowFall: WeatherType(
        weatherDescription = "Slight Snow Fall",
        iconResource = R.drawable.ic_snowy
    )

    object ModerateSnowFall: WeatherType(
        weatherDescription = "Moderate Snow Fall",
        iconResource = R.drawable.ic_heavysnow
    )

    object HeavySnowFall: WeatherType(
        weatherDescription = "Heavy Snow Fall",
        iconResource = R.drawable.ic_heavysnow
    )

    object SnowGrains: WeatherType(
        weatherDescription = "Snow Grains",
        iconResource = R.drawable.ic_heavysnow
    )

    object SlightRainShowers: WeatherType(
        weatherDescription = "Slight Rain Showers",
        iconResource = R.drawable.ic_rainshower
    )

    object ModerateRainShowers: WeatherType(
        weatherDescription = "Moderate Rain Showers",
        iconResource = R.drawable.ic_rainshower
    )

    object ViolentRainShowers: WeatherType(
        weatherDescription = "Violent Rain Showers",
        iconResource = R.drawable.ic_rainshower
    )

    object SlightSnowShowers: WeatherType(
        weatherDescription = "Light Snow Showers",
        iconResource = R.drawable.ic_snowy
    )

    object HeavySnowShowers: WeatherType(
        weatherDescription = "Heavy Snow Showers",
        iconResource = R.drawable.ic_snowy
    )

    object ModerateThunderstorm: WeatherType(
        weatherDescription = "Moderate Thunderstorm",
        iconResource = R.drawable.ic_thunder
    )

    object SlightHailThunderstorm: WeatherType(
        weatherDescription = "Thunderstorm with Slight Hail",
        iconResource = R.drawable.ic_rainythunder
    )

    object HeavyHailThunderstorm: WeatherType(
        weatherDescription = "Thunderstorm with Heavy Hail",
        iconResource = R.drawable.ic_rainythunder
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}