package com.kevinserrano.apps.leaguenow.domain.models

import com.kevinserrano.apps.leaguenow.data.local.Team

/**
 * Created by Kevin Serrano 28/08/21
 */
data class TeamModel(
    val idTeam: String = "",
    val strTeam: String = "",
    val strTeamBanner: String = "",
    val strTeamBadge: String = "",
    val strStadium: String = "",
    val strTeamJersey: String = "",
    val strFacebook: String = "",
    val strYoutube: String = "",
    val strDescriptionES: String = "",
    val intFormedYear: String = ""
){
    fun toLocalData() = Team(idTeam = idTeam,teamName = strTeam,banner = strTeamBanner,badge = strTeamBadge,
        stadium = strStadium,teamJersey = strTeamJersey,
        facebook = strFacebook,youtube = strYoutube,
        description = strDescriptionES,formedYear = intFormedYear)
}