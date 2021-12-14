package com.hakancskn.havadurumu.viewmodel


import androidx.lifecycle.MutableLiveData
import com.hakancskn.havadurumu.model.Forecasts
import com.hakancskn.havadurumu.model.LocationKey
import com.hakancskn.havadurumu.service.WeatherAPIService
import com.hakancskn.havadurumu.util.isMetric
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class LocationViewModel : BaseViewModel() {
    private val disposable = CompositeDisposable()
    private val weatherAPIService = WeatherAPIService()
    val locationError = MutableLiveData<Boolean>()
    val locationLoading = MutableLiveData<Boolean>()

    val locationKeyLiveData = MutableLiveData<String>()
    val forecastsLiveData = MutableLiveData<Forecasts>()

    fun getLocationKey(query: String) {
        locationLoading.value = true
        disposable.add(
            weatherAPIService.getLocationKey(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<LocationKey>() {
                    override fun onError(e: Throwable) {
                        locationLoading.value = false
                        locationError.value = true
                    }

                    override fun onSuccess(t: LocationKey) {
                        locationLoading.value = false
                        locationKeyLiveData.value = t.key
                    }
                })
        )


    }

    fun getForecasts(locationKey: String, isMetric: Boolean, language: String) {
        locationLoading.value = true
        disposable.add(
            weatherAPIService.getForecasts(locationKey, isMetric, language)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Forecasts>() {
                    override fun onError(e: Throwable) {
                        locationLoading.value = false
                        locationError.value = true
                    }

                    override fun onSuccess(t: Forecasts) {
                        locationLoading.value = false
                        forecastsLiveData.value = t
                    }
                })
        )


    }


}