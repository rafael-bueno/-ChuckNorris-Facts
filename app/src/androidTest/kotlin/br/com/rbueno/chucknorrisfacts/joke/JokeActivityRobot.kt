package br.com.rbueno.chucknorrisfacts.joke

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.*
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import br.com.rbueno.chucknorrisfacts.BaseInstrumentedTest
import br.com.rbueno.chucknorrisfacts.R
import br.com.rbueno.chucknorrisfacts.ui.home.EXTRA_CATEGORY
import br.com.rbueno.chucknorrisfacts.ui.joke.JokeActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

class JokeActivityRobot(private val baseInstrumentedTest: BaseInstrumentedTest) {

    @get:Rule
    var activityTestRule = ActivityTestRule(JokeActivity::class.java, false, false)

    fun givenSetupJokeResponse() = apply {
        baseInstrumentedTest.setupResponse(200, "joke.json", "jokes/random")
    }

    fun givenStartActivity() = apply {
        activityTestRule.launchActivity(Intent().apply {
            putExtra(EXTRA_CATEGORY, "category")
        })
    }

    fun givenStubToBrowser() = apply {
        intending(hasAction(Intent.ACTION_VIEW))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, Intent()))
    }

    fun whenClickInLink() = apply {
        onView(withId(R.id.text_link_joke)).perform(click())
    }

    fun thenOpenBrowser() = apply {
        intended(hasAction(Intent.ACTION_VIEW))
    }

    fun thenScreenFilled() = apply {
        onView(withId(R.id.image_joke)).check(matches(isDisplayed()))
        onView(withId(R.id.text_joke)).check(matches(isDisplayed()))
        onView(withId(R.id.text_link_joke)).check(matches(isDisplayed()))
    }

}