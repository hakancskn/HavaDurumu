package com.hakancskn.havadurumu.model

import com.google.gson.annotations.SerializedName

data class DayNight(
    @SerializedName("IconPhrase")
    val description:String?,
    @SerializedName("Icon")
    val icon:Int


)
