package br.com.rbueno.chucknorrisfacts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rbueno.chucknorrisfacts.model.Joke
import br.com.rbueno.chucknorrisfacts.repository.JokeRepository
import br.com.rbueno.chucknorrisfacts.util.handlerLoading
import javax.inject.Inject
import javax.inject.Singleton

class JokeViewModel(private val repository: JokeRepository) : BaseViewModel() {

    private val jokeMutableLiveData = MutableLiveData<Joke>()

    val jokeLiveData: LiveData<Joke>
        get() = jokeMutableLiveData


    fun getJokeByCategory(category: String) {
        disposables.add(
            repository.getJokeByCategory(category)
                .handlerLoading(loadingMutableLiveData)
                .subscribe({ jokeMutableLiveData.postValue(it) }, { handleError(it) })
        )
    }

    @Suppress("UNCHECKED_CAST")
    @Singleton
    class JokeViewModelFactory @Inject constructor(private val repository: JokeRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>) =
            JokeViewModel(repository) as T
    }
}
