package com.kevinserrano.apps.leaguenow.ui.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.kevinserrano.apps.leaguenow.utilities.FakeTeamData
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.ui.activities.HomeActivity
import com.kevinserrano.apps.leaguenow.ui.adapters.TeamsAdapter
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 *  Test class of [HomeFragment]
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class HomeFragmentTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<HomeActivity> = ActivityScenarioRule(HomeActivity::class.java)

    private val FAVORITE_TEAM_NAME_TEST = "Ath Bilbao"
    private val LIST_ITEM_IN_TEST = 2
    private val LIST_FAVORITE_IN_TEST = 0
    private val TEAM_IN_TEST = FakeTeamData.teamEntity.teams[LIST_ITEM_IN_TEST]

    @Test
    fun test_isRecyclerViewFavoritesVisible_onAppLaunch(){
        onView(withId(R.id.rv_favorites)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isRecyclerViewTeamsVisible_onAppLaunch(){
        onView(withId(R.id.rv_teams)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isAnimationLoadingVisible(){
        onView(withId(R.id.animation_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isAnimationLoadingVisibleAndNotVisible(){
        onView(withId(R.id.animation_view)).check(matches(not(isDisplayed())))
    }

    @Test
    fun test_isScrollViewHomeScrolled() {
        onView(withId(R.id.scrollViewHome))
            .perform(ViewActions.scrollTo(), click())
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_selectListFavoriteItem_isDetailActivityVisible() {
        onView(withId(R.id.rv_favorites)).perform(actionOnItemAtPosition<TeamsAdapter.TeamViewHolder>(
            LIST_FAVORITE_IN_TEST, click()
        ))
        // Confirm to DetailActivity and display title, description, team foundation year
        onView(withId(R.id.tv_team_name)).check(matches(withText(FAVORITE_TEAM_NAME_TEST)))
        onView(withId(R.id.tv_description)).check(matches(withText(TEAM_IN_TEST.strDescriptionES)))
        onView(withId(R.id.tv_team_foundation_year)).check(matches(withText(TEAM_IN_TEST.intFormedYear)))
    }

    @Test
    fun test_selectListTeamItem_isDetailActivityVisible() {
        onView(withId(R.id.rv_teams)).perform(actionOnItemAtPosition<TeamsAdapter.TeamViewHolder>(
            LIST_ITEM_IN_TEST, click()
        ))
        // Confirm to DetailActivity and display title, description, team foundation year
        onView(withId(R.id.tv_team_name)).check(matches(withText(TEAM_IN_TEST.strTeam)))
        onView(withId(R.id.tv_description)).check(matches(withText(TEAM_IN_TEST.strDescriptionES)))
        onView(withId(R.id.tv_team_foundation_year)).check(matches(withText(TEAM_IN_TEST.intFormedYear)))
    }

    @Test
    fun test_backNavigation_toHomeFragment() {
        onView(withId(R.id.rv_teams)).perform(actionOnItemAtPosition<TeamsAdapter.TeamViewHolder>(
            LIST_ITEM_IN_TEST, click()
        ))
        onView(withId(R.id.tv_team_name)).check(matches(withText(TEAM_IN_TEST.strTeam)))

        pressBack()

        onView(withId(R.id.rv_teams)).check(matches(isDisplayed()))
    }




}