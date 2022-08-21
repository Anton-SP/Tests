package com.geekbrains.tests.view.search


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.geekbrains.tests.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityRecordProgressBarExsistTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityRecordProgressBarExsistTest() {
        val progressBar = onView(
            allOf(
                withId(R.id.progressBar),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        progressBar.check(matches(isDisplayed()))
    }
}
