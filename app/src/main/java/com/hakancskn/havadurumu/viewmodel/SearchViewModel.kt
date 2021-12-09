package com.hakancskn.havadurumu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hakancskn.havadurumu.model.AutoComplete
import com.hakancskn.havadurumu.service.WeatherAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchViewModel : ViewModel() {

    val autoCompleteList = MutableLiveData<List<AutoComplete>>()
    val autoCompleteListError = MutableLiveData<Boolean>()
    val autoCompleteListLoading = MutableLiveData<Boolean>()

    private val disposable = CompositeDisposable()
    private val weatherAPIService = WeatherAPIService()


    fun getAutoComplete(query: String) {
        autoCompleteListLoading.value = true

        disposable.add(
            weatherAPIService.getAutoComplete(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<AutoComplete>>(){
                    override fun onSuccess(t: List<AutoComplete>) {
                        autoCompleteList.value = t
                        autoCompleteListLoading.value = false
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )



    }

}

