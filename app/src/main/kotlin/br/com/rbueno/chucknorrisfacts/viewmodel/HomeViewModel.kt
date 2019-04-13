package br.com.rbueno.chucknorrisfacts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rbueno.chucknorrisfacts.repository.HomeRepository
import br.com.rbueno.chucknorrisfacts.util.handlerLoading
import javax.inject.Inject
import javax.inject.Singleton

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    private val categoriesMutableLiveData = MutableLiveData<List<String>>()

    val categoriesLiveData: LiveData<List<String>>
        get() = categoriesMutableLiveData

    fun loadCategories() {
        disposables.add(
            repository.getCategories()
                .handlerLoading(loadingMutableLiveData)
                .subscribe({ categoriesMutableLiveData.postValue(it) }, { handleError(it) })
        )

    }

    @Suppress("UNCHECKED_CAST")
    @Singleton
    class HomeViewModelFactory @Inject constructor(private val repository: HomeRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>) =
            HomeViewModel(repository) as T
    }
}
