package com.hakancskn.havadurumu.util

import android.location.Location
import java.lang.StringBuilder

fun locationToLocationQuery(location:Location):String{
    val query = location.latitude.toString().plus(",").plus(location.longitude.toString())
    return query.toString()

}