package com.geekbrains.tests

import android.os.Build
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.DetailsActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])

class DetailsPresenterTest {

    private lateinit var scenario: ActivityScenario<DetailsActivity>
    private lateinit var presenter: DetailsPresenter

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
        presenter = DetailsPresenter(0)
        scenario.onActivity { presenter.onAttach(it) }
    }

    @After
    fun clear() {
        scenario.close()

    }


    @Test
    fun validateOnIncrement() {
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            presenter.onIncrement()
            TestCase.assertEquals("Number of results: 1", totalCountTextView.text)
        }
    }

    @Test
    fun validateOnDecrement() {
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            presenter.onDecrement()
            TestCase.assertEquals("Number of results: -1", totalCountTextView.text)
        }
    }

    @Test
    fun validateSetCounter() {
        presenter.setCounter(5)
        assertEquals(5, presenter.count)
    }

    @Test
    fun validateOnAttach() {
        scenario.moveToState(Lifecycle.State.CREATED).moveToState(Lifecycle.State.RESUMED)
            .onActivity {
                val presenter = it.getPresenter()
                assertEquals(it, presenter.getview())
            }
    }

    @Test
    fun validateOnDetach() {
        scenario.moveToState(Lifecycle.State.CREATED).moveToState(Lifecycle.State.RESUMED)
            .onActivity {
                val presenter = it.getPresenter()
                it.onBackPressed()
                assertNull(presenter.getview())
            }
    }


}