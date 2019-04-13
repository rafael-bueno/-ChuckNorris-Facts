package br.com.rbueno.chucknorrisfacts.repository

import br.com.rbueno.chucknorrisfacts.model.Joke
import br.com.rbueno.chucknorrisfacts.newtwork.ChuckNorrisFactsApi
import io.reactivex.Single

interface JokeRepository {
    fun getJokeByCategory(category: String): Single<Joke>
}

class NetworkJokeRepository(private val api: ChuckNorrisFactsApi) : JokeRepository {
    override fun getJokeByCategory(category: String) = api.getJokeByCategory(category)
}