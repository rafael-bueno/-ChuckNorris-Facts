package br.com.rbueno.chucknorrisfacts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    private val errorMutableLiveData = MutableLiveData<String>()
    protected val disposables = CompositeDisposable()
    protected var loadingMutableLiveData = MutableLiveData<Boolean>()

    val errorLiveData: LiveData<String>
        get() = errorMutableLiveData

    val loadingLiveData: LiveData<Boolean>
        get() = loadingMutableLiveData

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    protected fun handleError(throwable: Throwable) {
        errorMutableLiveData.postValue(throwable.message)
    }
}