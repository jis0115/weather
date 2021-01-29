package jis.lonepine.weather.domain.repository

import io.reactivex.rxjava3.core.Flowable
import jis.lonepine.weather.data.datasource.WeatherDataSource
import jis.lonepine.weather.data.entity.WeatherCityData
import jis.lonepine.weather.data.entity.WeatherInfo
import jis.lonepine.weather.data.remote.WeatherApi

class WeatherRepository(private val api:WeatherApi):WeatherDataSource {
    override fun searchCity(query: String): Flowable<WeatherCityData> {
        return api.getCityList(query = query)
    }

    override fun cityWeatherInfo(woeid: Int): Flowable<WeatherInfo> {
        return api.getWeatherInfo(woeid = woeid)
    }
}