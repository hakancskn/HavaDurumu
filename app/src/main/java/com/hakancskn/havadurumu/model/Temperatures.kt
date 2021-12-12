package com.hakancskn.havadurumu.model

import com.google.gson.annotations.SerializedName

data class Temperatures(
    @SerializedName("Minimum")
    val minimum:Temperatur?,
    @SerializedName("Maximum")
    val maximum:Temperatur?
)
