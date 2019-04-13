package br.com.rbueno.chucknorrisfacts.newtwork

import br.com.rbueno.chucknorrisfacts.model.Joke
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisFactsApi {

    @GET("jokes/categories")
    fun getCategories(): Single<List<String>>

    @GET("jokes/random")
    fun getJokeByCategory(@Query("category") category: String): Single<Joke>
}