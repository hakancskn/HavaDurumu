package com.hakancskn.havadurumu.util

import android.location.Location
import com.hakancskn.havadurumu.model.Temperatur
import com.hakancskn.havadurumu.model.Temperatures
import java.text.SimpleDateFormat
import java.util.*

fun locationToLocationQuery(location: Location): String {
    val query = location.latitude.toString().plus(",").plus(location.longitude.toString())
    return query.toString()

}

fun getDayNameForTime(time: Long): String {
    val simpleDateFormat = SimpleDateFormat("EEE - d", Locale.ENGLISH)
    return simpleDateFormat.format(time * 1000L)
}

fun getMaxMinTemperatur(temperatures: Temperatures): String {
    return getTemperatur(temperatures.maximum!!).plus("/")
        .plus(getTemperatur(temperatures.minimum!!))
}

fun getTemperatur(temperatur: Temperatur): String {
    return temperatur.value.toString().plus(temperatur.unit)
}