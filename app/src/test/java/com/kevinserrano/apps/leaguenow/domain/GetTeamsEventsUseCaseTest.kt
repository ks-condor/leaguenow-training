package com.kevinserrano.apps.leaguenow.domain

import com.kevinserrano.apps.leaguenow.CoroutinesTestRule
import com.kevinserrano.apps.leaguenow.MocksFactory.MOCKED_ID_TEAM
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamDomain
import com.kevinserrano.apps.leaguenow.common.Either
import com.kevinserrano.apps.leaguenow.data.repository.TeamsRepositoryImpl
import com.kevinserrano.apps.leaguenow.domain.usecase.GetTeamEventsUseCase
import com.kevinserrano.apps.leaguenow.presentation.mapper.TeamMapper
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*

/**
 *  Test class of [GetTeamEventsUseCase]
 */
@ExperimentalCoroutinesApi
class GetTeamsEventsUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private var teamsRepositoryImpl: TeamsRepositoryImpl = mockk()

    private var mapper:TeamMapper = mockk()

    private lateinit var getTeamEventsUseCase: GetTeamEventsUseCase

    @Before
    fun setUp(){
        getTeamEventsUseCase = GetTeamEventsUseCase(teamsRepositoryImpl)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    private fun verifyAllMocks() {
        confirmVerified(
            teamsRepositoryImpl
        )
    }

    @Test
    fun `Given getEvents of teamsRepositoryImp when this response then return a Either Right`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // Given
        val teamDomain = mockedTeamDomain
        val expectedResult = listOf(mockedTeamDomain.copy(idTeam = "FF5545454545"))

        every { mapper.fromEntityToDomain(any()) } returns teamDomain
        coEvery { teamsRepositoryImpl.getEvents(any()) } returns Either.Right(expectedResult)

        // When
        val result = getTeamEventsUseCase.run(MOCKED_ID_TEAM)

        // Verify
        assert(result is Either.Right)
        Assert.assertNotNull((result as Either.Right).b)
        Assert.assertSame(result.b,expectedResult)

        coVerify {
            teamsRepositoryImpl.getEvents(MOCKED_ID_TEAM)
        }
        verifyAllMocks()
    }

    @Test
    fun `Given getEvents of teamsRepositoryImp when this response then return a Either Left`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // Given
        val teamDomain = mockedTeamDomain
        val expectedResult = Exception("Not get list events")

        every { mapper.fromEntityToDomain(any()) } returns teamDomain
        coEvery { teamsRepositoryImpl.getEvents(any()) } returns Either.Left(expectedResult)

        // When
        val result = getTeamEventsUseCase.run(MOCKED_ID_TEAM)

        // Verify
        assert(result is Either.Left)
        Assert.assertNotNull((result as Either.Left).a)
        Assert.assertSame(result.a,expectedResult)

        coVerify {
            teamsRepositoryImpl.getEvents(MOCKED_ID_TEAM)
        }
        verifyAllMocks()
    }


}