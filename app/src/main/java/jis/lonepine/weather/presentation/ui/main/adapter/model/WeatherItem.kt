package jis.lonepine.weather.presentation.ui.main.adapter.model

data class WeatherItem(
        val iconUrl:String,
        val weather_state_name:String,
        val the_temp:Double,
        val humidity:Int,
        val applicable_date:String
)
{
    fun temp() = the_temp.toInt()
}
