package com.hakancskn.havadurumu.model

import com.google.gson.annotations.SerializedName

data class Forecasts (
    @SerializedName("Headline")
    val headLine: HeadLine?,
    @SerializedName("DailyForecasts")
    val dailyForecasts:List<DailyForecast>

        )
