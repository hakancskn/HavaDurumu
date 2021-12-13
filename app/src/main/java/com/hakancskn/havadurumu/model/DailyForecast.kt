package com.hakancskn.havadurumu.model

import com.google.gson.annotations.SerializedName
import com.hakancskn.havadurumu.util.getDayNameForTime
import com.hakancskn.havadurumu.util.getMaxMinTemperatur

class DailyForecast {

    @SerializedName("MobileLink")
    val mobileLink: String? = null

    @SerializedName("Temperature")
    val temperature: Temperatures? = null

    @SerializedName("EpochDate")
    val date: Long = 0

    @SerializedName("Day")
    val day :DayNight? = null

    @SerializedName("Night")
    val night :DayNight? = null

    fun getDayName(): String {
        return getDayNameForTime(date)
    }

    fun getTemperatures():String{
        return getMaxMinTemperatur(temperature!!)
    }

}


