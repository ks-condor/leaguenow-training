package com.kevinserrano.apps.leaguenow.ui.activities


import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.data.local.LeagueNowDatabase
import com.kevinserrano.apps.leaguenow.ui.adapters.SelecLeaguesAdapter
import com.kevinserrano.apps.leaguenow.ui.adapters.TeamsAdapter
import com.kevinserrano.apps.leaguenow.utilities.leagues
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 *  Test class of [HomeActivity]
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class HomeActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<HomeActivity> = ActivityScenarioRule(HomeActivity::class.java)

    private lateinit var context: Context

    private val LEAGUE_NAME_TEST = "Premier League"
    private val LIST_ITEM_IN_TEST = 1
    private val LEAGUE_IN_TEST = leagues[LIST_ITEM_IN_TEST]

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun test_isSelectLeagueFragmentVisible() {
        // Given - HomeActivity is launched

        // When - The button is clicked
        onView(withId(R.id.btn_retry)).perform(click())
        // Confirm show SelectLeagueFragment and display title option
        onView(withText(context.getString(R.string.select_league)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isRecyclerViewLeagueVisible_onSelectLeagueFragment(){
        onView(withId(R.id.btn_retry)).perform(click())
        onView(withId(R.id.rv_leagues)).check(matches(isDisplayed()))
    }


    @Test
    fun test_selectLeagueItem_isSelectLeagueFragmentVisible() {
        // Given - HomeActivity is launched

        // When - The button is clicked and item
        onView(withId(R.id.btn_retry)).perform(click())
        onView(withId(R.id.rv_leagues)).perform(
            RecyclerViewActions.actionOnItemAtPosition<SelecLeaguesAdapter.LeagueViewHolder>(
                LIST_ITEM_IN_TEST, click()
            )
        )

        // Then - HomeActivity btn message should be shown
        onView(withId(R.id.btn_retry)).check(matches(withText(context.getString(R.string.title_selected_league_s,
            LEAGUE_IN_TEST.strLeagueAlternate))))
    }

}