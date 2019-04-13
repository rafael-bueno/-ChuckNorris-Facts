package br.com.rbueno.chucknorrisfacts.repository

import br.com.rbueno.chucknorrisfacts.newtwork.ChuckNorrisFactsApi
import io.reactivex.Single

interface HomeRepository {
    fun getCategories(): Single<List<String>>
}

class NetworkHomeRepository(private val api: ChuckNorrisFactsApi) : HomeRepository {
    override fun getCategories() = api.getCategories()
}