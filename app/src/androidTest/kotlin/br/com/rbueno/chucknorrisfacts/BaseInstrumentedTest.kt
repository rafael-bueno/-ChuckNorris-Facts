package br.com.rbueno.chucknorrisfacts

import androidx.test.platform.app.InstrumentationRegistry
import br.com.concretesolutions.requestmatcher.InstrumentedTestRequestMatcherRule
import br.com.concretesolutions.requestmatcher.model.HttpMethod
import br.com.rbueno.chucknorrisfacts.di.DaggerAppComponentTest
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule

abstract class BaseInstrumentedTest {

    @Rule
    @JvmField
    var serverRule = InstrumentedTestRequestMatcherRule()

    private lateinit var app: AppTest

    @Before
    open fun setUp() {
        app = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .applicationContext as AppTest
        app.baseUrl = serverRule.url("/").toString()
        injectActivityTest()
    }

    private fun injectActivityTest() {
        DaggerAppComponentTest
            .builder()
            .application(app)
            .build()
            .inject(this)
    }

    fun setupResponse(
        statusCode: Int,
        responsePath: String,
        endsWith: String,
        httpMethod: HttpMethod = HttpMethod.GET
    ) {

        serverRule.addFixture(statusCode, responsePath)
            .ifRequestMatches()
            .methodIs(httpMethod)
            .pathMatches(CoreMatchers.endsWith(endsWith))
    }
}