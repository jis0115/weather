package jis.lonepine.weather.data.datasource

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import jis.lonepine.weather.data.entity.WeatherCityData
import jis.lonepine.weather.data.entity.WeatherInfo

interface WeatherDataSource {
    fun searchCity(query:String): Flowable<WeatherCityData>
    fun cityWeatherInfo(woeid:Int): Flowable<WeatherInfo>
}