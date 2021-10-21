package com.kevinserrano.apps.leaguenow.domain.usecase

import com.kevinserrano.apps.leaguenow.repository.TeamsRepository


/**
 * Created by Kevin Serrano 28/08/21
 */

class GetTeamEventsUseCase(private val teamsRepository: TeamsRepository) {
    suspend fun run(idTeam: String) = teamsRepository.getEvents(idTeam)
}