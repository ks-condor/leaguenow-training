package com.kevinserrano.apps.leaguenow.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kevinserrano.apps.leaguenow.CoroutinesTestRule
import com.kevinserrano.apps.leaguenow.MocksFactory
import com.kevinserrano.apps.leaguenow.MocksFactory.MOCKED_ID_LEAGUE
import com.kevinserrano.apps.leaguenow.common.Either
import com.kevinserrano.apps.leaguenow.data.repository.TeamsRepositoryImpl
import com.kevinserrano.apps.leaguenow.domain.usecase.GetTeamsUseCase
import com.kevinserrano.apps.leaguenow.presentation.viewModels.HomeViewModel
import com.nhaarman.mockitokotlin2.given
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock

/**
 *  Test class of [GetTeamsUseCase]
 */
@ExperimentalCoroutinesApi
class GetTeamsUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private var teamsRepositoryImpl: TeamsRepositoryImpl = mockk()

    private lateinit var getTeamsUseCase: GetTeamsUseCase

    @Before
    fun setUp(){
        getTeamsUseCase = GetTeamsUseCase(teamsRepositoryImpl)
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
    fun `Given getTeams of teamsRepositoryImp when this response then return a Either Right`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // Given
        val expectedResult = listOf(MocksFactory.mockedTeamDomain.copy(idTeam = "FF5545454545"))

        coEvery { teamsRepositoryImpl.getTeams(any()) } returns Either.Right(expectedResult)

        // When
        val result = getTeamsUseCase.run(MOCKED_ID_LEAGUE)

        // Verify
        assert(result is Either.Right)
        Assert.assertNotNull((result as Either.Right).b)
        Assert.assertSame(result.b,expectedResult)

        coVerify {
            teamsRepositoryImpl.getTeams(MOCKED_ID_LEAGUE)
        }
        verifyAllMocks()
    }

    @Test
    fun `Given faile of teamsRepositoryImp when this response then return a Either Left`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        // Given
        val expectedResult = Exception("Not get list teams")

        coEvery { teamsRepositoryImpl.getTeams(any()) } returns Either.Left(expectedResult)

        // When
        val result = getTeamsUseCase.run(MOCKED_ID_LEAGUE)

        // Verify
        assert(result is Either.Left)
        Assert.assertNotNull((result as Either.Left).a)
        Assert.assertSame(result.a,expectedResult)

        coVerify {
            teamsRepositoryImpl.getTeams(MOCKED_ID_LEAGUE)
        }
        verifyAllMocks()
    }


}