package jis.lonepine.weather.presentation.ui.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jis.lonepine.weather.presentation.ui.main.adapter.MainRecyclerAdapter
import jis.lonepine.weather.presentation.ui.main.adapter.model.WeatherTableUI

@BindingAdapter("setWeatherItem")
fun setItemList(recyclerView: RecyclerView,itemList:List<WeatherTableUI>)
{
    if (recyclerView.adapter == null){
        recyclerView.adapter = MainRecyclerAdapter()
    }

    (recyclerView.adapter as? MainRecyclerAdapter)?.let {
        it.updateList(itemList.toList())
    }
}

@BindingAdapter("loadGlide")
fun loadGlideImage(imageView: ImageView,imageUrl:String?)
{
    imageUrl?.let {
        Glide.with(imageView.context).load("https://www.metaweather.com/static/img/weather/png/64/$it.png").fitCenter().into(imageView)
    }
}