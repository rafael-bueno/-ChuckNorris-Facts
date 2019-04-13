package br.com.rbueno.chucknorrisfacts.di

import br.com.rbueno.chucknorrisfacts.ui.home.HomeActivity
import br.com.rbueno.chucknorrisfacts.ui.joke.JokeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): HomeActivity


    @ContributesAndroidInjector
    abstract fun bindJokeActivity(): JokeActivity
}