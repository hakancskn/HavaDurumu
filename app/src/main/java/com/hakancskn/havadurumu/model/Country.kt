package com.hakancskn.havadurumu.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("LocalizedName")
    val countryname: String?
)
