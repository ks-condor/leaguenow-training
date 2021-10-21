package com.kevinserrano.apps.leaguenow.repository

import com.kevinserrano.apps.leaguenow.domain.models.TeamModel
import com.kevinserrano.apps.leaguenow.common.Either


/**
 * Created by Kevin Serrano 28/08/21
 */

interface TeamsRepository {
    suspend fun getTeams(idLeguea: String): Either<Throwable, List<TeamModel>>
    suspend fun getEvents(idTeamm: String): Either<Throwable, List<TeamModel>>
}