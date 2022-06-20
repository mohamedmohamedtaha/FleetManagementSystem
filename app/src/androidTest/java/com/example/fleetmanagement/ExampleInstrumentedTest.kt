package com.example.fleetmanagement

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fleetmanagement.ui.authentication.fragment.LoginFragment
import com.example.fleetmanagement.ui.home.activity.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkLogin(){
        onView(withId(R.id.imageProfileUser)).perform(click())
        onView(withId(R.id.textViewLogIn)).perform(click())
        onView(withId(R.id.editTextEmail)).perform(typeText("mohamed"))
        onView(withId(R.id.editTextPassword)).perform(typeText("01007919166"))
        onView(withId(R.id.loginButton)).perform(scrollTo()).perform(click())

    }
}













