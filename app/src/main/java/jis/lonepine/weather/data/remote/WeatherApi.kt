package jis.lonepine.weather.data.remote

import io.reactivex.rxjava3.core.Flowable
import jis.lonepine.weather.data.entity.WeatherCityData
import jis.lonepine.weather.data.entity.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("/api/location/search/")
    fun getCityList(@Query("query") query:String): Flowable<WeatherCityData>

    @GET("/api/location/{woeid}")
    fun getWeatherInfo(@Path(value = "woeid",encoded = false) woeid:Int): Flowable<WeatherInfo>
}