package jis.lonepine.weather.data.entity

data class ConsolidatedWeather(
    val applicable_date: String,
    val humidity: Int, //습기
    val id: Long,
    val the_temp: Double, //온도
    val weather_state_abbr: String,
    val weather_state_name: String,
)