package com.hakancskn.havadurumu.util

import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson

import android.content.Context.MODE_PRIVATE

import com.google.gson.reflect.TypeToken
import com.hakancskn.havadurumu.model.AutoComplete
import java.lang.reflect.Type


val PREFERENCES = "WEATHER_PREFERENCES"
val listSize = 5

fun getLocationKeys(context: Context): ArrayList<AutoComplete>? {
    val locationKeyList: ArrayList<AutoComplete>?

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES, MODE_PRIVATE)
    val gson = Gson()
    val json = sharedPreferences.getString("locationKeys", null)

    val type: Type = object : TypeToken<ArrayList<AutoComplete?>?>() {}.type

    locationKeyList = gson.fromJson<Any>(json, type) as ArrayList<AutoComplete>?

    return locationKeyList
}

fun saveData(context: Context, locationKeyList: ArrayList<AutoComplete>) {

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES, MODE_PRIVATE)

    val editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(locationKeyList)
    editor.putString("locationKeys", json)

    editor.apply()
}

fun addSearchData(context: Context, serchItem: AutoComplete) {
    var locationKeyList = getLocationKeys(context)
    if (locationKeyList == null) {
        locationKeyList = arrayListOf()
    }

    val isSearch = locationKeyList?.toList()?.find {
        it.cityKey == serchItem.cityKey
    }
    if (isSearch == null) {

        if (locationKeyList.size >= listSize) {
            locationKeyList.removeAt(0)
        }
        locationKeyList.add(serchItem)
        saveData(context, locationKeyList)
    }
}

fun isLanguageTr(context: Context): Boolean {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES, MODE_PRIVATE)
    return sharedPreferences.getBoolean("language", false)
}

fun changeLanguage(context: Context) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES, MODE_PRIVATE)

    sharedPreferences.edit().putBoolean("language", !isLanguageTr(context)).apply()
}


fun isMetric(context: Context): Boolean {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES, MODE_PRIVATE)
    return sharedPreferences.getBoolean("metric", false)
}

fun changeMetric(context: Context) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES, MODE_PRIVATE)

    sharedPreferences.edit().putBoolean("metric", !isMetric(context)).apply()
}


