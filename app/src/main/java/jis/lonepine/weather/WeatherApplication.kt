package jis.lonepine.weather

import androidx.multidex.MultiDexApplication
import jis.lonepine.weather.presentation.di.moduleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication: MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApplication)
            modules(moduleList)
        }
    }
}