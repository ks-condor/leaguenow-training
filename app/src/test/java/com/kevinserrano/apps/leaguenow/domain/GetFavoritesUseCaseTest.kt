package com.kevinserrano.apps.leaguenow.domain

import com.kevinserrano.apps.leaguenow.CoroutinesTestRule
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamsDB
import com.kevinserrano.apps.leaguenow.data.repository.FavoritesRepositoryImpl
import com.kevinserrano.apps.leaguenow.domain.usecase.GetFavoritesUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*

/**
 *  Test class of [GetFavoritesUseCase]
 */
@ExperimentalCoroutinesApi
class GetFavoritesUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private var favoritesRepositoryImpl: FavoritesRepositoryImpl = mockk()

    private lateinit var getFavoritesUseCase: GetFavoritesUseCase

    @Before
    fun setUp(){
        getFavoritesUseCase = GetFavoritesUseCase(favoritesRepositoryImpl)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given run of getFavoritesUseCase when this response then return a Flow List of Team`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // given
        val expectedResult = mockedTeamsDB

        coEvery { favoritesRepositoryImpl.getFavorites() } returns flow { emit(expectedResult) }

        // When
        val result = getFavoritesUseCase.run()

        // Verify
        result.collect {
            Assert.assertEquals(expectedResult,it)
        }
    }



}