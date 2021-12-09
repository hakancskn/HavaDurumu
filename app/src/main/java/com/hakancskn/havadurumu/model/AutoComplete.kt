package com.hakancskn.havadurumu.model

import com.google.gson.annotations.SerializedName

data class AutoComplete(
    @SerializedName("Key")
    val cityKey: String?,
    @SerializedName("LocalizedName")
    val name: String?,
    @SerializedName("Country")
    val country: Country?
)