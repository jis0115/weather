package jis.lonepine.weather.data.entity

data class WeatherInfo(
    val consolidated_weather: List<ConsolidatedWeather>,
    val title: String,
    val woeid: Int
)