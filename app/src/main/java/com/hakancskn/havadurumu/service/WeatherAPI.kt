package com.hakancskn.havadurumu.service

import com.hakancskn.havadurumu.model.AutoComplete
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("locations/v1/cities/autocomplete")
    fun getAutoComplete(
        @Query("apikey") apikey:String = "",
        @Query("q") query:String = ""
    ): Single<List<AutoComplete>>
}