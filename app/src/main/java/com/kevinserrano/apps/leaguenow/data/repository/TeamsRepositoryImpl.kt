package com.kevinserrano.apps.leaguenow.data.repository

import com.kevinserrano.apps.leaguenow.BuildConfig
import com.kevinserrano.apps.leaguenow.domain.models.TeamModel
import com.kevinserrano.apps.leaguenow.common.Either
import com.kevinserrano.apps.leaguenow.data.remote.LeagueNowApi
import com.kevinserrano.apps.leaguenow.repository.TeamsRepository
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Kevin Serrano 28/08/21
 */
@Singleton
class TeamsRepositoryImpl @Inject constructor(private val leagueNowApi: LeagueNowApi) : TeamsRepository {
    override suspend fun getTeams(idLeguea:String): Either<Throwable, List<TeamModel>> = try {
        Either.Right(leagueNowApi.getTeams(idLeague = idLeguea,key = BuildConfig.KeyTheSportsDB).teams.map { it.toDomain() })
    } catch (ex: Exception) {
        Either.Left(ex)
    }

    override suspend fun getEvents(idTeamm: String): Either<Throwable, List<TeamModel>> = try {
        Either.Right(leagueNowApi.getEvents(idTeamm = idTeamm,
            key = BuildConfig.KeyTheSportsDB).teams.map { it.toDomain() })
    } catch (ex: Exception) {
        Either.Left(ex)
    }
}