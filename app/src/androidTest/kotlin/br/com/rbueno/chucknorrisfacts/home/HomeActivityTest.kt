package br.com.rbueno.chucknorrisfacts.home

import androidx.test.espresso.intent.Intents
import br.com.rbueno.chucknorrisfacts.BaseInstrumentedTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest : BaseInstrumentedTest() {

    private val robot = HomeActivityRobot(this)

    @Before
    fun before() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun shouldViewScreenFilled_WhenApiReturnsSuccess() {
        robot
            .givenSetupCategoriesResponse()
            .givenStartActivity()
            .thenScreenFilled()
    }

    @Test
    fun shouldNavigateToJokeScreen_WhenClickInCategory() {
        robot
            .givenSetupCategoriesResponse()
            .givenStubToJokeActivity()
            .givenStartActivity()
            .whenClickInCategory()
            .thenOpenJokeActivity()
    }
}