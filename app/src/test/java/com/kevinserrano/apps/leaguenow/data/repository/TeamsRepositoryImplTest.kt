package com.kevinserrano.apps.leaguenow.data.repository

import com.kevinserrano.apps.leaguenow.CoroutinesTestRule
import com.kevinserrano.apps.leaguenow.MocksFactory.MOCKED_ID_LEAGUE
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamDomain
import com.kevinserrano.apps.leaguenow.MocksFactory.mockedTeamsEntity
import com.kevinserrano.apps.leaguenow.common.Either
import com.kevinserrano.apps.leaguenow.data.remote.LeagueNowApi
import com.kevinserrano.apps.leaguenow.data.repository.TeamsRepositoryImpl
import com.kevinserrano.apps.leaguenow.presentation.mapper.TeamMapper
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*

/**
 *  Test class of [TeamsRepositoryImpl]
 */
@ExperimentalCoroutinesApi
class TeamsRepositoryImplTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var repositoryImpl: TeamsRepositoryImpl

    private var leagueNowApi: LeagueNowApi = mockk()

    private var mapper: TeamMapper = mockk()

    @Before
    fun setUp(){
        repositoryImpl = TeamsRepositoryImpl(leagueNowApi,mapper)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given getTeams of leagueNowApi when this response then return a TeamsEntity`() = coroutinesTestRule.testDispatcher.runBlockingTest{
            // given
            val expectedResult = mockedTeamsEntity
            val teamsDomian = mockedTeamDomain

            every { mapper.fromEntityToDomain(any()) } returns teamsDomian
            coEvery { leagueNowApi.getTeams(any(), any()) } returns expectedResult

            // When
            val result = repositoryImpl.getTeams(MOCKED_ID_LEAGUE)

            // Verify
            assert(result is Either.Right)
            Assert.assertNotNull((result as Either.Right).b)
            //Assert.assertSame(result.b,teamsDomianlist)
    }

}


