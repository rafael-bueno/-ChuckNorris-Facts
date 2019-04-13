package br.com.rbueno.chucknorrisfacts.joke

import androidx.test.espresso.intent.Intents
import br.com.rbueno.chucknorrisfacts.BaseInstrumentedTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class JokeActivityTest : BaseInstrumentedTest() {

    private val robot = JokeActivityRobot(this)

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
            .givenSetupJokeResponse()
            .givenStartActivity()
            .thenScreenFilled()
    }

    @Test
    fun shouldOpenBrowser_WhenUserClickInJokeLink() {
        robot
            .givenSetupJokeResponse()
            .givenStubToBrowser()
            .givenStartActivity()
            .whenClickInLink()
            .thenOpenBrowser()

    }
}