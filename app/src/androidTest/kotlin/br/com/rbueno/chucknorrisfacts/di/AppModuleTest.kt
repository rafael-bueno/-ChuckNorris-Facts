package br.com.rbueno.chucknorrisfacts.di

import br.com.rbueno.chucknorrisfacts.AppTest
import br.com.rbueno.chucknorrisfacts.CustomApp
import br.com.rbueno.chucknorrisfacts.newtwork.ChuckNorrisFactsApi
import br.com.rbueno.chucknorrisfacts.newtwork.NetworkConfig
import br.com.rbueno.chucknorrisfacts.repository.HomeRepository
import br.com.rbueno.chucknorrisfacts.repository.JokeRepository
import br.com.rbueno.chucknorrisfacts.repository.NetworkHomeRepository
import br.com.rbueno.chucknorrisfacts.repository.NetworkJokeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModuleTest {
    @Singleton
    @Provides
    fun provideApi(app: AppTest): ChuckNorrisFactsApi = NetworkConfig.createApi(app.getUrl())

    @Singleton
    @Provides
    fun provideHomeRepository(api: ChuckNorrisFactsApi): HomeRepository = NetworkHomeRepository(api)

    @Singleton
    @Provides
    fun provideJokeRepository(api: ChuckNorrisFactsApi): JokeRepository = NetworkJokeRepository(api)

}
