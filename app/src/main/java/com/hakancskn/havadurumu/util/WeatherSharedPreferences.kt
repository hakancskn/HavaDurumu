package com.hakancskn.havadurumu.util

import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson

import android.content.Context.MODE_PRIVATE

import com.google.gson.reflect.TypeToken
import com.hakancskn.havadurumu.model.AutoComplete
import java.lang.reflect.Type


class WeatherSharedPreferences {

    private val PREFERENCES = "WEATHER_PREFERENCES"

    private fun getLocationKeys(context: Context): ArrayList<AutoComplete> {
        val locationKeyList: ArrayList<AutoComplete>

        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("PREFERENCES", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("locationKeys", null)

        val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type

        locationKeyList = gson.fromJson<Any>(json, type) as ArrayList<AutoComplete>

        return locationKeyList
    }

    private fun saveData(context: Context, locationKeyList: ArrayList<AutoComplete>) {

        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("shared preferences", MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(locationKeyList)
        editor.putString("locationKeys", json)

        editor.apply()
    }

}