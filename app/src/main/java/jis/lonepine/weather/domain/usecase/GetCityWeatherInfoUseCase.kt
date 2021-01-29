package jis.lonepine.weather.domain.usecase

import jis.lonepine.weather.domain.repository.WeatherRepository

class GetCityWeatherInfoUseCase(private val repository: WeatherRepository) {
    fun getWeatherInfo(woeid:Int) = repository.cityWeatherInfo(woeid = woeid)
}