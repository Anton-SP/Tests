package com.geekbrains.tests

import android.os.Build
import android.widget.Button
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.DetailsActivity
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
    fun setup(){
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
        presenter = DetailsPresenter(0)
    }

    @After
    fun clear (){
        scenario.close()

    }


    @Test
    fun test1(){
       presenter.onIncrement()
       assertEquals(1,presenter.count)
    }

    @Test
    fun test2(){
        presenter.onDecrement()
        assertEquals(-1,presenter.count)
    }

    @Test
    fun test3(){
        presenter.setCounter(5)
        assertEquals(5,presenter.count)
    }


}