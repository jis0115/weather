package jis.lonepine.weather.presentation.di

import jis.lonepine.weather.data.remote.WeatherApi
import jis.lonepine.weather.domain.repository.WeatherRepository
import jis.lonepine.weather.domain.usecase.GetCityWeatherInfoUseCase
import jis.lonepine.weather.domain.usecase.SearchCityUseCase
import jis.lonepine.weather.presentation.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.HEADERS
}

val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(30,TimeUnit.SECONDS)
        .build()

val retrofit: Retrofit = Retrofit
    .Builder()
    .baseUrl("https://www.metaweather.com")
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build()

private val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)

val networkModule = module {
    single { weatherApi }
}

val repositoryModule = module {

    factory { WeatherRepository(get()) }
}

val useCaseModule = module {
    factory { GetCityWeatherInfoUseCase(get()) }
    factory { SearchCityUseCase(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get(),get()) }
}

val moduleList = listOf(
    viewModelModule, useCaseModule, repositoryModule,networkModule
)