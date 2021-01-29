package jis.lonepine.weather.presentation.ui.main.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import jis.lonepine.weather.databinding.HolderWeatherItemBinding
import jis.lonepine.weather.presentation.ui.main.adapter.model.WeatherTableUI

class WeatherHolder(private val binding:HolderWeatherItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(data: WeatherTableUI.WeatherRowUI) {
        binding.city = data.city
        binding.today = data.today
        binding.tomorrow = data.tomorrow
    }
}