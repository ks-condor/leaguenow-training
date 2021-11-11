package com.kevinserrano.apps.leaguenow.domain.usecase

import com.kevinserrano.apps.leaguenow.domain.repository.TeamsRepository
import javax.inject.Inject

/**
 * Created by Kevin Serrano 28/08/21
 */
class GetTeamsUseCase @Inject constructor(private val teamsRepository: TeamsRepository) {
    suspend fun run(idLeguea: String = "4335") = teamsRepository.getTeams(idLeguea)
}
