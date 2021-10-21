package com.kevinserrano.apps.leaguenow.presentation.mapper

import com.kevinserrano.apps.leaguenow.data.local.Team
import com.kevinserrano.apps.leaguenow.domain.models.TeamModel

/**
 * Created by Kevin Serrano 28/08/21
 */
object TeamMapper {
    fun fromDBToPresentation(teams: List<Team>): List<TeamModel> {
        return teams.map {
            TeamModel(
                idTeam = it.idTeam,
                strTeam = it.teamName,
                strTeamBanner= it.banner,
                strTeamBadge = it.badge,
                strStadium = it.stadium,
                strTeamJersey = it.teamJersey,
                strFacebook = it.facebook,
                strYoutube = it.youtube,
                strDescriptionES = it.description,
                intFormedYear = it.formedYear
            )
        }
    }
}