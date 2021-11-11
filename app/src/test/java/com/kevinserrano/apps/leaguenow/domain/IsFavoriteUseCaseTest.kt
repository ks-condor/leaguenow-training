package com.kevinserrano.apps.leaguenow.domain

import com.kevinserrano.apps.leaguenow.CoroutinesTestRule
import com.kevinserrano.apps.leaguenow.MocksFactory.MOCKED_ID_TEAM
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamsDB
import com.kevinserrano.apps.leaguenow.data.repository.FavoritesRepositoryImpl
import com.kevinserrano.apps.leaguenow.domain.usecase.DeleteFavoriteUseCase
import com.kevinserrano.apps.leaguenow.domain.usecase.GetFavoritesUseCase
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
 *  Test class of [IsFavoriteUseCase]
 */
@ExperimentalCoroutinesApi
class IsFavoriteUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private var favoritesRepositoryImpl: FavoritesRepositoryImpl = mockk()

    private lateinit var isFavoriteUseCase: IsFavoriteUseCase

    @Before
    fun setUp(){
        isFavoriteUseCase = IsFavoriteUseCase(favoritesRepositoryImpl)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given existFavorite of favoritesRepositoryImpl when this response then return a Boolean`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // given
        val expectedResult = true

        coEvery { favoritesRepositoryImpl.existFavorite(any()) } returns expectedResult

        // When
        val result = isFavoriteUseCase.run(MOCKED_ID_TEAM)

        // Verify
        Assert.assertEquals(expectedResult,result)
    }

}