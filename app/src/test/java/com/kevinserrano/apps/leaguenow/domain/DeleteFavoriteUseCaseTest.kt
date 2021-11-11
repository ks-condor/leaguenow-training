package com.kevinserrano.apps.leaguenow.domain

import com.kevinserrano.apps.leaguenow.CoroutinesTestRule
import com.kevinserrano.apps.leaguenow.MocksFactory.MOCKED_ID_TEAM
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamsDB
import com.kevinserrano.apps.leaguenow.data.repository.FavoritesRepositoryImpl
import com.kevinserrano.apps.leaguenow.domain.usecase.DeleteFavoriteUseCase
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
 *  Test class of [DeleteFavoriteUseCase]
 */
@ExperimentalCoroutinesApi
class DeleteFavoriteUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private var favoritesRepositoryImpl: FavoritesRepositoryImpl = mockk()

    private lateinit var deleteFavoriteUseCase: DeleteFavoriteUseCase

    @Before
    fun setUp(){
        deleteFavoriteUseCase = DeleteFavoriteUseCase(favoritesRepositoryImpl)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given deleteFavorite of favoritesRepositoryImpl when this response then return a Int`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // given
        val expectedResult = 1

        coEvery { favoritesRepositoryImpl.deleteFavorite(any()) } returns expectedResult

        // When
        val result = deleteFavoriteUseCase.run(MOCKED_ID_TEAM)

        // Verify
        Assert.assertEquals(expectedResult,result)
    }

}