package com.hakancskn.havadurumu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hakancskn.havadurumu.R
import com.hakancskn.havadurumu.databinding.ItemForecastBinding
import com.hakancskn.havadurumu.model.DailyForecast


class ForecastAdapter(private val forecastList: ArrayList<DailyForecast>):RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(var view: ItemForecastBinding):RecyclerView.ViewHolder(view.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastAdapter.ForecastViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemForecastBinding>(inflater, R.layout.item_forecast,parent,false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastAdapter.ForecastViewHolder, position: Int) {
        holder.view.forecast = forecastList[position]

    }

    override fun getItemCount(): Int {
       return forecastList.size
    }

    fun updateForecastList(updateForecastList: List<DailyForecast>){
        forecastList.clear()
        forecastList.addAll(updateForecastList)
        notifyDataSetChanged()
    }


}