package com.geekbrains.tests

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.search.MainActivity
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activitySearch_IsWorking() {
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).perform(replaceText("algol"),
            closeSoftKeyboard())
        onView(withId(R.id.searchEditText)).perform(pressImeActionButton())
        onView(withId(R.id.totalCountTextView)).check(matches(withText("Number of results: 42")))
    }


    @Test
    fun activity_AsserNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activity_toDetailsActivityButtonIsDisplayed() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_toDetailsActivityButtonIsVisible() {
        onView(withId(R.id.toDetailsActivityButton)).check(
            matches(
                withEffectiveVisibility(
                    Visibility.VISIBLE
                )
            )
        )
    }

    @Test
    fun activity_toDetialsActivityButtonIsValidLabel(){
        val  assertion = matches(withText("to details"))
            onView(withId(R.id.toDetailsActivityButton)).check(assertion)
    }

    @Test
    fun activity_toDetailsActivityButtonClickStartNewActivity(){
        onView(withId(R.id.toDetailsActivityButton)).perform(click())
        TestCase.assertNotSame(Lifecycle.State.DESTROYED, scenario.state)
    }

    @Test
    fun activity_searchEditTextIsDisplayed() {
        onView(withId(R.id.searchEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_searchEditTextIsVisible() {
        onView(withId(R.id.searchEditText)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun activity_searchEditTextIsEmpty() {
        val assertion = matches(withText(""))
        onView(withId(R.id.searchEditText)).check(assertion)
    }

    @Test
    fun activity_searchEditTextIsEditable() {
        val assertion = matches(withText("tom"))
        onView(withId(R.id.searchEditText)).perform(typeText("tom")).check(assertion)
    }

    @Test
    fun activity_searchEditTextValidateHint(){
        val assertion = matches(withHint("Enter keyword e.g. android"))
        onView(withId(R.id.searchEditText)).check(assertion)
    }
}