package com.kevinserrano.apps.leaguenow.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.kevinserrano.apps.leaguenow.data.models.Team
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 *  Test class of [LeagueNowDatabase]
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LeagueNowDatabaseTest: TestCase() {


    private lateinit var favoritesDao: FavoritesDao
    private lateinit var db: LeagueNowDatabase

    private val mockedTeam = Team(
        idTeam = "",
        teamName = "Barcelona",
        banner = "none",
        badge = "none",
        leagueName = "HUHUH yy",
        stadium = "None Stadium",
        teamJersey = "Cally",
        facebook = "None",
        youtube = "None",
        description = "...none",
        formedYear = "1990"
    )

    @Before
    fun createDB() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, LeagueNowDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        favoritesDao = db.favoritesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    fun readFavorites() = runBlocking {
        val favorite = mockedTeam
        favoritesDao.insert(favorite)
        val result = favoritesDao.getFavorites()
        val favorites = result.first()
        Assert.assertTrue(favorites.contains(favorite))
    }

    @Test
    fun writeAndReadFavorite() = runBlocking {
            val favorite = mockedTeam
            val result = favoritesDao.insert(favorite)
            Assert.assertEquals(result,1L)
    }

}