package com.kevinserrano.apps.leaguenow.data.repository

import com.kevinserrano.apps.leaguenow.BuildConfig
import com.kevinserrano.apps.leaguenow.common.Either
import com.kevinserrano.apps.leaguenow.data.remote.LeagueNowApi
import com.kevinserrano.apps.leaguenow.domain.models.TeamDomain
import com.kevinserrano.apps.leaguenow.presentation.mapper.TeamMapper
import com.kevinserrano.apps.leaguenow.domain.repository.TeamsRepository
import com.kevinserrano.apps.leaguenow.utilities.FakeTeamData
import javax.inject.Inject


/**
 * Created by Kevin Serrano 28/08/21
 */
class TeamsRepositoryImpl @Inject constructor(private val leagueNowApi: LeagueNowApi,
                                              private val mapper:TeamMapper) : TeamsRepository {
    override suspend fun getTeams(idLeguea:String): Either<Throwable, List<TeamDomain>> = try {
        //Either.Right(leagueNowApi.getTeams(idLeague = idLeguea,key = BuildConfig.KeyTheSportsDB).teams.map { mapper.fromEntityToDomain(it) })
        Either.Right(FakeTeamData.teamEntity.teams.map { mapper.fromEntityToDomain(it) })
    } catch (ex: Exception) {
        Either.Left(ex)
    }

    override suspend fun getEvents(idTeamm: String): Either<Throwable, List<TeamDomain>> = try {
        Either.Right(leagueNowApi.getEvents(idTeamm = idTeamm,
            key = BuildConfig.KeyTheSportsDB).teams.map { mapper.fromEntityToDomain(it) })
    } catch (ex: Exception) {
        Either.Left(ex)
    }
}