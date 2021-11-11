package com.kevinserrano.apps.leaguenow.domain

import com.kevinserrano.apps.leaguenow.CoroutinesTestRule
import com.kevinserrano.apps.leaguenow.MocksFactory.MOCKED_ID_TEAM
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeam
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamsDB
import com.kevinserrano.apps.leaguenow.data.repository.FavoritesRepositoryImpl
import com.kevinserrano.apps.leaguenow.domain.usecase.DeleteFavoriteUseCase
import com.kevinserrano.apps.leaguenow.domain.usecase.GetFavoritesUseCase
import com.kevinserrano.apps.leaguenow.domain.usecase.InsertFavoriteUseCase
import com.kevinserrano.apps.leaguenow.domain.usecase.IsFavoriteUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*

/**
 *  Test class of [InsertFavoriteUseCase]
 */
@ExperimentalCoroutinesApi
class InsertFavoriteUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private var favoritesRepositoryImpl: FavoritesRepositoryImpl = mockk()

    private lateinit var insertFavoriteUseCase: InsertFavoriteUseCase

    @Before
    fun setUp(){
        insertFavoriteUseCase = InsertFavoriteUseCase(favoritesRepositoryImpl)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given existFavorite of favoritesRepositoryImpl when this response then return a Boolean`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // given
        val team = mockedTeam
        val expectedResult = 1L

        coEvery { favoritesRepositoryImpl.insertFavorite(any()) } returns expectedResult

        // When
        val result = insertFavoriteUseCase.run(team)

        // Verify
        Assert.assertEquals(expectedResult,result)
    }

}