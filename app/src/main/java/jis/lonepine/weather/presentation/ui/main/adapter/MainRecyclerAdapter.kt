package jis.lonepine.weather.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import jis.lonepine.weather.R
import jis.lonepine.weather.databinding.HolderWeatherHeaderItemBinding
import jis.lonepine.weather.databinding.HolderWeatherItemBinding
import jis.lonepine.weather.presentation.ui.main.adapter.holder.WeatherHeaderHolder
import jis.lonepine.weather.presentation.ui.main.adapter.holder.WeatherHolder
import jis.lonepine.weather.presentation.ui.main.adapter.model.WeatherTableUI

class MainRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemList = mutableListOf<WeatherTableUI>()

    fun updateList(newList:List<WeatherTableUI>)
    {
        val callback = WeatherDiffUtilCallBack(itemList,newList)
        val diffResult = DiffUtil.calculateDiff(callback)
        itemList.clear()
        itemList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType)
        {
            R.layout.holder_weather_header_item -> WeatherHeaderHolder(HolderWeatherHeaderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else -> WeatherHolder(HolderWeatherItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder)
        {
            is WeatherHeaderHolder->{}
            is WeatherHolder->{
                holder.bind(itemList[position] as WeatherTableUI.WeatherRowUI)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position])
        {
            is WeatherTableUI.HeaderUI->{
                R.layout.holder_weather_header_item
            }
            is WeatherTableUI.WeatherRowUI->{
                R.layout.holder_weather_item
            }
        }
    }
}