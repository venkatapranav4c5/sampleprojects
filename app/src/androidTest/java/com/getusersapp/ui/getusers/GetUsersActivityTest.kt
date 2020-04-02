package com.getusersapp.ui.getusers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.getusersapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class GetUsersActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(GetUsersActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.rootLayoutGetUsers)).check(matches(isDisplayed()))

        onView(withId(R.id.btnGetUsers)).check(matches(isDisplayed()))

        onView(withId(R.id.btnGetUsers)).perform(click())

        onView(withId(R.id.rootLayoutUserList)).check(matches(isDisplayed()))
    }

}