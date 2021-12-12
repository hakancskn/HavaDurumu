package com.hakancskn.havadurumu.model

import com.google.gson.annotations.SerializedName

data class DailyForecast(
@SerializedName("MobileLink")
val mobileLink  : String?,
@SerializedName("Temperature")
val temperature : Temperatures?,


)
