package com.hakancskn.havadurumu.viewmodel

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel(): ViewModel() {
    val locationLiveData = MutableLiveData<Location>()


}