package com.kevinserrano.apps.leaguenow.presentacion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kevinserrano.apps.leaguenow.CoroutinesTestRule
import com.kevinserrano.apps.leaguenow.MocksFactory.MOCKED_ID_TEAM
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeam
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamDomain
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamPresentation
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamsPresentation
import com.kevinserrano.apps.leaguenow.common.Either
import com.kevinserrano.apps.leaguenow.domain.usecase.*
import com.kevinserrano.apps.leaguenow.presentation.mapper.TeamMapper
import com.kevinserrano.apps.leaguenow.presentation.state.State
import com.kevinserrano.apps.leaguenow.presentation.viewModels.DetailsTeamViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.rules.TestRule

/**
 * Test class of [DetailsTeamViewModel]
 */
@ExperimentalCoroutinesApi
class DetailsTeamViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private var getTeamEventsUseCase: GetTeamEventsUseCase = mockk()

    private var isFavoritesUseCase: IsFavoriteUseCase = mockk()

    private var insertFavoriteUseCase: InsertFavoriteUseCase = mockk()

    private var deleteFavoriteUseCase: DeleteFavoriteUseCase = mockk()

    private var mapper: TeamMapper = mockk()

    private lateinit var detailsTeamViewModel: DetailsTeamViewModel

    @Before
    fun setUp(){
        detailsTeamViewModel = DetailsTeamViewModel(getTeamEventsUseCase,isFavoritesUseCase,
            insertFavoriteUseCase,deleteFavoriteUseCase,mapper)
    }

    @After
    fun tearDown(){
        clearAllMocks()
    }

    @Test
    fun `Given run of getTeamEventsUseCase when this response then return success a List of TeamsPresentation`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // Given
        val teamsDomain = listOf(mockedTeamDomain)
        val teamsPresentation = mockedTeamsPresentation
        val expectedResult = State.Success(teamsPresentation)

        every { mapper.fromDBToPresentation(any()) } returns teamsPresentation
        coEvery { getTeamEventsUseCase.run(any()) } returns Either.Right(teamsDomain)

        // When
        detailsTeamViewModel.fetchTeamEvents(MOCKED_ID_TEAM)

        // Verify
        Assert.assertEquals(detailsTeamViewModel.stateGetTeamEvents.value,expectedResult)

    }

    @Test
    fun `Given run of getTeamEventsUseCase when this response then return a faile`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // Given
        val teamsPresentation = mockedTeamsPresentation
        val expectedResult = State.Failed("Events not found")
        val handledError = Exception("Events not found")

        every { mapper.fromDBToPresentation(any()) } returns teamsPresentation
        coEvery { getTeamEventsUseCase.run(any()) } returns Either.Left(handledError)

        // When
        detailsTeamViewModel.fetchTeamEvents(MOCKED_ID_TEAM)

        // Verify
        Assert.assertEquals(detailsTeamViewModel.stateGetTeamEvents.value,expectedResult)

    }

    @Test
    fun `Given run of isFavoritesUseCase when this response then return a Boolean`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // Given
        val expectedResult = true

        coEvery { isFavoritesUseCase.run(any()) } returns true

        // When
        detailsTeamViewModel.isFavorite(MOCKED_ID_TEAM)

        // Verify
        Assert.assertEquals(detailsTeamViewModel.stateIsFavorite.value,expectedResult)

    }

    @Test
    fun `favorite should return an expected success Boolean`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // Given
        val teamDB = mockedTeam.copy(idTeam = "HH67676HH")
        val teamPresentation = mockedTeamPresentation.copy(idTeam = "HH67676HH")
        val expectedResult = true

        every { mapper.toDB(any()) } returns teamDB
        coEvery { deleteFavoriteUseCase.run(any()) } returns 1
        coEvery { insertFavoriteUseCase.run(any()) } returns 1

        // When
        detailsTeamViewModel.favorite(teamPresentation)

        // Verify
        Assert.assertEquals(detailsTeamViewModel.stateIsFavorite.value,expectedResult)

    }

}
