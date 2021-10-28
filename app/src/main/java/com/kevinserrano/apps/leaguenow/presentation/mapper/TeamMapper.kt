package com.kevinserrano.apps.leaguenow.presentation.mapper

import com.kevinserrano.apps.leaguenow.data.models.Team
import com.kevinserrano.apps.leaguenow.data.models.TeamsEntity
import com.kevinserrano.apps.leaguenow.domain.models.TeamDomain
import com.kevinserrano.apps.leaguenow.presentation.models.TeamPresentation

/**
 * Created by Kevin Serrano 28/08/21
 */
object TeamMapper {
    fun fromDBToPresentation(teams: List<Team>): List<TeamPresentation> {
        return teams.map {
            TeamPresentation(
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
    fun fromDomainToPresentation(teams: List<TeamDomain>): List<TeamPresentation> {
        return teams.map {
            TeamPresentation(
                idTeam = it.idTeam,
                strTeam = it.strTeam,
                strTeamBanner= it.strTeamBanner,
                strTeamBadge = it.strTeamBadge,
                strStadium = it.strStadium,
                strTeamJersey = it.strTeamJersey,
                strFacebook = it.strFacebook,
                strYoutube = it.strYoutube,
                strDescriptionES = it.strDescriptionES,
                intFormedYear = it.intFormedYear
            )
        }
    }
    fun fromEntityToDomain(team: TeamsEntity.Team): TeamDomain {
        return TeamDomain(
                idTeam = team.idTeam?:"",
                strTeam = team.strTeam?:"",
                strTeamBanner= team.strTeamBanner?:"",
                strTeamBadge = team.strTeamBadge?:"",
                strStadium = team.strStadium?:"",
                strTeamJersey = team.strTeamJersey?:"",
                strFacebook = team.strFacebook?:"",
                strYoutube = team.strYoutube?:"",
                strDescriptionES = team.strDescriptionES?:"",
                intFormedYear = team.intFormedYear?:""
            )
    }
    fun toDB(team: TeamPresentation): Team {
        return Team(idTeam = team.idTeam,teamName = team.strTeam,banner = team.strTeamBanner,
            badge = team.strTeamBadge,
            stadium = team.strStadium,teamJersey = team.strTeamJersey,
            facebook = team.strFacebook,youtube = team.strYoutube,
            description = team.strDescriptionES,formedYear = team.intFormedYear)
    }
}