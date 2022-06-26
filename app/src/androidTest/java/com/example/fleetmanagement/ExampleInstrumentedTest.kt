package com.example.fleetmanagement

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fleetmanagement.ui.home.activity.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)
//  @get:Rule
//   val activity = ActivityScenario.launch(MainActivity::class.java)

    @Test
    fun checkLoginWrongValidation(){
        onView(withId(R.id.imageProfileUser)).perform(click())
        onView(withId(R.id.textViewLogIn)).perform(click())
        onView(withId(R.id.editTextEmail)).perform(typeText("hishamantar2000@gmail.com"))
        onView(withId(R.id.editTextPassword)).perform(typeText("Testdddd@123"))
        onView(withId(R.id.loginButton)).perform(scrollTo()).perform(click())
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()))
    }
    @Test
    fun checkLoginRightValidation(){
        onView(withId(R.id.imageProfileUser)).perform(click())
        onView(withId(R.id.textViewLogIn)).perform(click())
        onView(withId(R.id.editTextEmail)).perform(typeText("hishamantar2000@gmail.com"))
        onView(withId(R.id.editTextPassword)).perform(typeText("Test@123"))
        onView(withId(R.id.loginButton)).perform(scrollTo()).perform(click())
        onView(withId(R.id.recyclerViewTasks)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.materialCardViewNoData)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.recyclerViewTasks)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun checkLogoutValidation(){
        onView(withId(R.id.imageProfileUser)).perform(click())
        onView(withId(R.id.textViewLogOut)).perform(click())
        onView(withId(R.id.materialCardViewNoData)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.recyclerViewTasks)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        //pressBack()
    }
}