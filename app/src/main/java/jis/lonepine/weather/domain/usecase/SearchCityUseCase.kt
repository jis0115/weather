package jis.lonepine.weather.domain.usecase

import jis.lonepine.weather.domain.repository.WeatherRepository

class SearchCityUseCase(private val repository: WeatherRepository) {
    fun searchCityList(query:String) = repository.searchCity(query = query)
}