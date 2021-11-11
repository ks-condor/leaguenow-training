package com.kevinserrano.apps.leaguenow.presentacion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kevinserrano.apps.leaguenow.CoroutinesTestRule
import com.kevinserrano.apps.leaguenow.MocksFactory.MOCKED_ID_LEAGUE
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamsDB
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamDomain
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamsPresentation
import com.kevinserrano.apps.leaguenow.common.Either
import com.kevinserrano.apps.leaguenow.domain.usecase.GetFavoritesUseCase
import com.kevinserrano.apps.leaguenow.domain.usecase.GetTeamsUseCase
import com.kevinserrano.apps.leaguenow.presentation.mapper.TeamMapper
import com.kevinserrano.apps.leaguenow.presentation.state.State
import com.kevinserrano.apps.leaguenow.presentation.viewModels.HomeViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.rules.TestRule


/**
 *  Test class of [HomeViewModel]
 */
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val getTeamsUseCase: GetTeamsUseCase = mockk()

    private var getFavoritesUseCase: GetFavoritesUseCase = mockk()

    private var mapper:TeamMapper = mockk()

    private lateinit var homeViewModel: HomeViewModel


    @Before
    fun setUp(){
        homeViewModel = HomeViewModel(getTeamsUseCase,getFavoritesUseCase,mapper)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `onGetAllFavorites should return an expected success list of teams favorites`() = coroutinesTestRule.testDispatcher.runBlockingTest{
            // Given
            val teams = mockedTeamsDB
            val teamsPresentation = mockedTeamsPresentation
            val result = State.Success(teamsPresentation)

            every { mapper.fromDBToPresentation(any()) } returns teamsPresentation
            coEvery { getFavoritesUseCase.run() } returns flow { emit(teams) }

            // When
            homeViewModel.getFavorites()

            // Verify
            Assert.assertEquals(homeViewModel.stateGetFavorites.value,result)
    }


    @Test
    fun `onGetAllTeams should return an expected success list of teams`() = coroutinesTestRule.testDispatcher.runBlockingTest{
            // Given
            val teamsDomain= listOf(mockedTeamDomain)
            val teamsPresentation = mockedTeamsPresentation
            val result = State.Success(teamsPresentation)

            every { mapper.fromDomainToPresentation(any()) } returns teamsPresentation
            coEvery { getTeamsUseCase.run() } returns Either.Right(teamsDomain)

            // When
            homeViewModel.fetchTeams(MOCKED_ID_LEAGUE)

            // Verify
            Assert.assertEquals(homeViewModel.stateGetTeams.value,result)
    }

}
