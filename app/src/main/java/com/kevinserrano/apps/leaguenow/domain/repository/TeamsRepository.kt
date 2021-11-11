package com.kevinserrano.apps.leaguenow.domain.repository

import com.kevinserrano.apps.leaguenow.common.Either
import com.kevinserrano.apps.leaguenow.domain.models.TeamDomain


/**
 * Created by Kevin Serrano 28/08/21
 */

interface TeamsRepository {
    suspend fun getTeams(idLeguea: String): Either<Throwable, List<TeamDomain>>
    suspend fun getEvents(idTeamm: String): Either<Throwable, List<TeamDomain>>
}