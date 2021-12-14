package com.hakancskn.havadurumu.service

import com.google.gson.GsonBuilder
import com.hakancskn.havadurumu.model.AutoComplete
import com.hakancskn.havadurumu.model.Forecasts
import com.hakancskn.havadurumu.model.LocationKey
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherAPIService {
    private val BASE_URL = "http://dataservice.accuweather.com/"
    private val API_KEY = "LYe1ss69qycdWh64bIqNhA3Mgw7bBEhT"

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherAPI::class.java)

    fun getAutoComplete(query: String): Single<List<AutoComplete>> {
        return api.getAutoComplete(apikey = API_KEY, query = query)
    }

    fun getLocationKey(query: String): Single<LocationKey> {
        return api.getLocationKeyForLocation(apikey = API_KEY, query = query)
    }

    fun getForecasts(locationKey: LocationKey): Single<Forecasts> {
        return api.get5DaysForecasts(apikey = API_KEY, locationKey = locationKey.key.toString(),language= "tr-tr")
    }

    fun getForecasts(locationKey:String):Single<Forecasts> {
        return api.get5DaysForecasts(apikey = API_KEY, locationKey = locationKey,language= "tr-tr")
    }

}