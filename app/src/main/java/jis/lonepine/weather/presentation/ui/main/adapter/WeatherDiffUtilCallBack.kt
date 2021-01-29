package jis.lonepine.weather.presentation.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import jis.lonepine.weather.presentation.ui.main.adapter.model.WeatherTableUI

class WeatherDiffUtilCallBack(private val oldList:List<WeatherTableUI>, private val newList:List<WeatherTableUI>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (oldList[oldItemPosition] is WeatherTableUI.WeatherRowUI && newList[newItemPosition] is WeatherTableUI.WeatherRowUI)
        {
            (oldList[oldItemPosition] as WeatherTableUI.WeatherRowUI).weoid == (newList[newItemPosition] as WeatherTableUI.WeatherRowUI).weoid
        }
        else
        {
            oldList[oldItemPosition].javaClass == newList[newItemPosition].javaClass
        }
   }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (oldList[oldItemPosition] is WeatherTableUI.WeatherRowUI && newList[newItemPosition] is WeatherTableUI.WeatherRowUI)
        {
            val oldItem = (oldList[oldItemPosition] as WeatherTableUI.WeatherRowUI)
            val newItem = (newList[oldItemPosition] as WeatherTableUI.WeatherRowUI)
            oldItem.today == newItem.today && oldItem.tomorrow == newItem.tomorrow
        }
        else
        {
            oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}