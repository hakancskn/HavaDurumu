package com.hakancskn.havadurumu.service

import com.hakancskn.havadurumu.model.AutoComplete
import com.hakancskn.havadurumu.model.Forecasts
import com.hakancskn.havadurumu.model.LocationKey
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAPI {

    @GET("locations/v1/cities/autocomplete")
    fun getAutoComplete(
        @Query("apikey") apikey:String = "",
        @Query("q") query:String = ""
    ): Single<List<AutoComplete>>
    @GET("/locations/v1/cities/geoposition/search")
    fun getLocationKeyForLocation(
        @Query("apikey") apikey:String = "",
        @Query("q") query:String = ""
    ):Single<LocationKey>

    @GET("/forecasts/v1/daily/5day/{key}")
    fun get5DaysForecasts(
        @Path("key") locationKey: String ="",
        @Query("apikey") apikey:String = "",
        @Query("language") language:String = ""
    ):Single<Forecasts>


}