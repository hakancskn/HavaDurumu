package com.hakancskn.havadurumu.model

import com.google.gson.annotations.SerializedName

data class Temperatur(
    @SerializedName("Value")
    val value:Int?,
    @SerializedName("Unit")
    val unit:String?

)
