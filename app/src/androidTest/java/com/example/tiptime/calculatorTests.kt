package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.containsString

@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {
        //Types the value
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("100.00"))
            .perform(ViewActions.closeSoftKeyboard())
        //Click the calculate button
        onView(withId(R.id.calculate_button))
            .perform(click())
        //read the amount
        onView(withId(R.id.tip_amount))
            .check(matches(withText(containsString("$20.00"))))
    }
    @Test
    fun calculate_18_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("100.00"))
            .perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.option_eighteen_percent))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_amount))
            .check(matches(withText(containsString("$18.00"))))
    }
    @Test
    fun calculate_15_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("100.00"))
            .perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.option_fifteen_percent))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_amount))
            .check(matches(withText(containsString("$15.00"))))
    }

    @Test
    fun calculate_round_up() {
        //insert amount
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("100.10"))
            .perform(ViewActions.closeSoftKeyboard())
        //select to no round up
        onView(withId(R.id.round_tip))
            .perform(click())

        //click calculate button
        onView(withId(R.id.calculate_button))
            .perform(click())

        //check if the value was not rounded up

        onView(withId(R.id.tip_amount))
            .check(matches(withText(containsString("$20.02"))))

    }
}