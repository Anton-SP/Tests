package com.geekbrains.tests.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {


    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    //Контекст нам понадобится для запуска нужных экранов и получения packageName
    private val context = ApplicationProvider.getApplicationContext<Context>()

    //Путь к классам нашего приложения, которые мы будем тестировать
    private val packageName = context.packageName

    @Before
    fun setup() {
//Для начала сворачиваем все приложения, если у нас что-то запущено
        uiDevice.pressHome()
//Запускаем наше приложение
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
//Ждем, когда приложение откроется на смартфоне чтобы начать тестировать его элементы
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    companion object {
        private const val TIMEOUT = 8000L
    }

    @Test
    fun test_MainActivityIsStarted() {
//Через uiDevice находим editText
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
//Проверяем на null
        Assert.assertNotNull(editText)
    }

    @Test
    fun test_SearchIsPositive() {
//Через uiDevice находим editText
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
//Устанавливаем значение
        editText.text = "UiAutomator"

        uiDevice.findObject(By.res(packageName, "searchMainActivityButton")).click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text.toString(), "Number of results: 42")
    }

    @Test
    fun test_OpenDetailsScreen() {
//Находим кнопку
        val toDetails = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )
//Кликаем по ней
        toDetails.click()
//Ожидаем конкретного события: появления текстового поля totalCountTextView.
//Это будет означать, что DetailsScreen открылся и это поле видно на экране.
        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextViewDetail")),
                TIMEOUT
            )
        Assert.assertEquals(changedText.text, "Number of results: 0")
    }

    @Test
    fun test_ValidateDetailsScreen() {
//Через uiDevice находим editText
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
//Устанавливаем значение
        editText.text = "UiAutomator"

        uiDevice.findObject(By.res(packageName, "searchMainActivityButton")).click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            ).text
        uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        ).click()

        val datailTextCount = uiDevice.wait(
            Until.findObject(By.res(packageName, "totalCountTextViewDetail")),
            TIMEOUT
        )
        Assert.assertEquals(changedText, datailTextCount.text)
    }

    @Test
    fun test_ValidateIncrement() {
//Находим кнопку
        val toDetails = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )

        toDetails.click()

        val incrementButton = uiDevice.wait(
            Until.findObject(By.res(packageName, "incrementButton")),
            TIMEOUT
        )
        incrementButton.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextViewDetail")),
                TIMEOUT
            )
        Assert.assertEquals("Number of results: 1", changedText.text)

    }

    @Test
    fun test_ValidateDecrement() {

        val toDetails = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )

        toDetails.click()

        val decrementButton = uiDevice.wait(
            Until.findObject(By.res(packageName, "decrementButton")),
            TIMEOUT
        )
        decrementButton.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextViewDetail")),
                TIMEOUT
            )
        Assert.assertEquals("Number of results: -1", changedText.text)

    }


}