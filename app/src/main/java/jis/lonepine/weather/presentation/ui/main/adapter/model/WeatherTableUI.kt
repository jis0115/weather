package jis.lonepine.weather.presentation.ui.main.adapter.model

sealed class WeatherTableUI
{
    object HeaderUI:WeatherTableUI()
    data class WeatherRowUI(val city: String,val weoid:Int, var today:WeatherItem?=null,var tomorrow:WeatherItem?=null):WeatherTableUI()
}