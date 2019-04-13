package br.com.rbueno.chucknorrisfacts

import br.com.rbueno.chucknorrisfacts.di.DaggerAppComponentTest


class AppTest : CustomApp() {

    lateinit var baseUrl: String

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger() {
        DaggerAppComponentTest.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun getUrl() = baseUrl
}
