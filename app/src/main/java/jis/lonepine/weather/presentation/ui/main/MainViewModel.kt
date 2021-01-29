package jis.lonepine.weather.presentation.ui.main

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import jis.lonepine.weather.domain.usecase.GetCityWeatherInfoUseCase
import jis.lonepine.weather.domain.usecase.SearchCityUseCase
import jis.lonepine.weather.presentation.ui.base.DisposableViewModel
import jis.lonepine.weather.presentation.ui.base.NotNullMutableLiveData
import jis.lonepine.weather.presentation.ui.base.SingleLiveEvent
import jis.lonepine.weather.presentation.ui.main.adapter.model.WeatherItem
import jis.lonepine.weather.presentation.ui.main.adapter.model.WeatherTableUI

class MainViewModel(
    private val searchCityUseCase: SearchCityUseCase,
    private val getCityWeatherInfoUseCase: GetCityWeatherInfoUseCase
    ) : DisposableViewModel() {
    private val _dataLoadFinish = SingleLiveEvent<Any>()
    val dataLoadFinish:LiveData<Any> = _dataLoadFinish

    private val _showLoadingDialog = SingleLiveEvent<Any>()
    val showLoadingDialog:LiveData<Any> = _showLoadingDialog

    private val _dismissLoadingDialog = SingleLiveEvent<Any>()
    val dismissLoadingDialog:LiveData<Any> = _dismissLoadingDialog

    private var weatherMap:MutableMap<Int,WeatherTableUI> = mutableMapOf()

    private val _weatherItemList = NotNullMutableLiveData(listOf<WeatherTableUI>())
    val weatherItemList:LiveData<List<WeatherTableUI>> = _weatherItemList

    fun searchCity()
    {
        addDisposable(
            Flowable.fromCallable {
                _showLoadingDialog.call()
            }.subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .flatMap {
                searchCityUseCase.searchCityList("se")
            }
            .flatMap { city ->
                val rowMap = mutableMapOf<Int,WeatherTableUI.WeatherRowUI>()
                city.forEach {cityInfo->
                    rowMap[cityInfo.woeid] = WeatherTableUI.WeatherRowUI(
                            city = cityInfo.title,
                            weoid = cityInfo.woeid,
                            today = null,
                            tomorrow = null
                    )
                }
                Flowable.just(rowMap)

            }.observeOn(AndroidSchedulers.mainThread())
            .flatMap {map->
                weatherMap[0] = WeatherTableUI.HeaderUI
                weatherMap.putAll(map)
                _weatherItemList.postValue(weatherMap.map { it.value }.toList())
                _dismissLoadingDialog.call()
                Flowable.just(map.keys)
            }
            .observeOn(Schedulers.io())
            .flatMap {woeidList->
                Flowable.merge(woeidList.map {
                    getCityWeatherInfoUseCase.getWeatherInfo(it)
                })
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                _weatherItemList.postValue(weatherMap.map { it.value }.toList())
                _dataLoadFinish.call()
            }
            .subscribe({data->
                val subList = data.consolidated_weather.subList(0,2)
                weatherMap[data.woeid] = WeatherTableUI.WeatherRowUI(
                    city = data.title,
                    weoid = data.woeid,
                    today = WeatherItem(
                            iconUrl = subList.first().weather_state_abbr,
                            weather_state_name = subList.first().weather_state_name,
                            the_temp = subList.first().the_temp,
                            humidity = subList.first().humidity,
                            applicable_date = subList.first().applicable_date
                    ),
                    tomorrow = WeatherItem(
                            iconUrl = subList.last().weather_state_abbr,
                            weather_state_name = subList.last().weather_state_name,
                            the_temp = subList.last().the_temp,
                            humidity = subList.last().humidity,
                            applicable_date = subList.last().applicable_date
                    )
                )
                _weatherItemList.postValue(weatherMap.map { it.value }.toList())
            },{
                it.printStackTrace()
            })
        )
    }
}