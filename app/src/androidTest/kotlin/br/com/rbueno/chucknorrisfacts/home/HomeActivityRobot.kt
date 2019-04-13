package br.com.rbueno.chucknorrisfacts.home

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import br.com.rbueno.chucknorrisfacts.BaseInstrumentedTest
import br.com.rbueno.chucknorrisfacts.R
import br.com.rbueno.chucknorrisfacts.ui.home.EXTRA_CATEGORY
import br.com.rbueno.chucknorrisfacts.ui.home.HomeActivity
import br.com.rbueno.chucknorrisfacts.ui.joke.JokeActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule

class HomeActivityRobot(private val baseInstrumentedTest: BaseInstrumentedTest) {

    @get:Rule
    var activityTestRule = ActivityTestRule(HomeActivity::class.java, false, false)

    fun givenSetupCategoriesResponse() = apply {
        baseInstrumentedTest.setupResponse(200, "categories_list.json", "jokes/categories")
    }

    fun givenStartActivity() = apply {
        activityTestRule.launchActivity(Intent())
    }

    fun givenStubToJokeActivity() = apply {
        intending(hasComponent(JokeActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, Intent()))
    }

    fun whenClickInCategory() = apply {
        onView(withId(R.id.recycler_categories)).perform(
            RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
    }

    fun thenScreenFilled() = apply {
        onView(withId(R.id.recycler_categories)).check(matches(isDisplayed()))
    }

    fun thenOpenJokeActivity() = apply {
        intended(
            allOf(
                hasExtraWithKey(EXTRA_CATEGORY),
                hasComponent(JokeActivity::class.java.name)
            )
        )
    }
}