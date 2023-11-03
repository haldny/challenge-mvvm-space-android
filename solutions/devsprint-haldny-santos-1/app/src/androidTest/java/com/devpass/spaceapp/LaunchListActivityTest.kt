package com.devpass.spaceapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devpass.spaceapp.presentation.launchList.LaunchListActivity
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchViewHolder
import com.devpass.spaceapp.utils.waitUntilGoneAction
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaunchListActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LaunchListActivity::class.java)

    @Test
    fun whenClickOnListItemShoulderGoToLaunchActivityWithItem() {
        onView(withId(R.id.lottie_loading))
            .perform(
                waitUntilGoneAction(3000L)
            )

        onView(
            allOf(
                withId(R.id.rv_launches),
                isDisplayed()
            )
        ).perform(
            actionOnItemAtPosition<LaunchViewHolder>(2, click())
        )
    }
}
