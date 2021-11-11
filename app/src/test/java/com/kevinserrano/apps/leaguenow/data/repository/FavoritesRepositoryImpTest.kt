package com.kevinserrano.apps.leaguenow.data.repository

import com.kevinserrano.apps.leaguenow.CoroutinesTestRule
import com.kevinserrano.apps.leaguenow.MocksFactory.MOCKED_ID_TEAM
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeam
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamsDB
import com.kevinserrano.apps.leaguenow.data.local.FavoritesDao
import com.kevinserrano.apps.leaguenow.data.repository.FavoritesRepositoryImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*

/**
 *  Test class of [FavoritesRepositoryImpl]
 */
@ExperimentalCoroutinesApi
class FavoritesRepositoryImpTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var repositoryImpl: FavoritesRepositoryImpl

    private var favoritesDao: FavoritesDao = mockk()

    @Before
    fun setUp(){
        repositoryImpl = FavoritesRepositoryImpl(favoritesDao)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given getTeams of favoritesRepositoryImpl when this response then return a Flow List of Team`() = coroutinesTestRule.testDispatcher.runBlockingTest{
            // given
            val expectedResult = mockedTeamsDB

            coEvery { repositoryImpl.getFavorites() } returns flow { emit(expectedResult) }

            // When
            val result = repositoryImpl.getFavorites()

            // Verify
            result.collect {
                Assert.assertEquals(expectedResult,it)
            }
    }

    @Test
    fun `Given insertFavorite of favoritesRepositoryImpl when this response then return success Long`() = coroutinesTestRule.testDispatcher.runBlockingTest{
            // given
            val expectedResult = 1L

            coEvery { repositoryImpl.insertFavorite(any()) } returns expectedResult

            // When
            val result = repositoryImpl.insertFavorite(mockedTeam.copy(idTeam = "HU787878T"))

            // Verify
            Assert.assertEquals(expectedResult,result)
    }

    @Test
    fun `Given insertFavorite of favoritesRepositoryImpl when this response then return faile Long`() = coroutinesTestRule.testDispatcher.runBlockingTest{
            // given
            val expectedResult = 0L

            coEvery { repositoryImpl.insertFavorite(any()) } returns expectedResult

            // When
            val result = repositoryImpl.insertFavorite(mockedTeam.copy(idTeam = "HU787878T"))

            // Verify
            Assert.assertEquals(expectedResult,result)
    }

    @Test
    fun `Given deleteFavorite of favoritesRepositoryImpl when this response then return Int`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // given
        val expectedResult = 1

        coEvery { repositoryImpl.deleteFavorite(any()) } returns expectedResult

        // When
        val result = repositoryImpl.deleteFavorite(MOCKED_ID_TEAM)

        // Verify
        Assert.assertEquals(expectedResult,result)
    }


    @Test
    fun `Given existFavorite of favoritesRepositoryImpl when this response then return Boolean`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // given
        val expectedResult = true

        coEvery { repositoryImpl.existFavorite(any()) } returns expectedResult

        // When
        val result = repositoryImpl.existFavorite(MOCKED_ID_TEAM)

        // Verify
        Assert.assertEquals(expectedResult,result)
    }

}


