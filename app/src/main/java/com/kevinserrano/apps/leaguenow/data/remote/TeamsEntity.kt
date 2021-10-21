package com.kevinserrano.apps.leaguenow.data.remote

import com.google.gson.annotations.SerializedName
import com.kevinserrano.apps.leaguenow.domain.models.TeamModel
import com.kevinserrano.apps.leaguenow.utilities.completeUrl

/**
 * Created by Kevin Serrano 28/08/21
 */

class TeamsEntity(
    @SerializedName("teams")
    val teams: List<Team> = emptyList()
) {
    data class Team(
        @SerializedName("idAPIfootball")
        val idAPIfootball: String? = null,
        @SerializedName("idLeague")
        val idLeague: String? = null,
        @SerializedName("idSoccerXML")
        val idSoccerXML: String? = null,
        @SerializedName("idTeam")
        val idTeam: String? = null,
        @SerializedName("intFormedYear")
        val intFormedYear: String? = null,
        @SerializedName("intLoved")
        val intLoved: Any,
        @SerializedName("intStadiumCapacity")
        val intStadiumCapacity: String? = null,
        @SerializedName("strAlternate")
        val strAlternate: String? = null,
        @SerializedName("strCountry")
        val strCountry: String? = null,
        @SerializedName("strDescriptionEN")
        val strDescriptionEN: String? = null,
        @SerializedName("strDescriptionES")
        val strDescriptionES: String? = null,
        @SerializedName("strDivision")
        val strDivision: Any,
        @SerializedName("strFacebook")
        val strFacebook: String? = null,
        @SerializedName("strGender")
        val strGender: String? = null,
        @SerializedName("strInstagram")
        val strInstagram: String? = null,
        @SerializedName("strKeywords")
        val strKeywords: String? = null,
        @SerializedName("strLeague")
        val strLeague: String? = null,
        @SerializedName("strLocked")
        val strLocked: String? = null,
        @SerializedName("strManager")
        val strManager: String? = null,
        @SerializedName("strRSS")
        val strRSS: String? = null,
        @SerializedName("strSport")
        val strSport: String? = null,
        @SerializedName("strStadium")
        val strStadium: String? = null,
        @SerializedName("strStadiumDescription")
        val strStadiumDescription: String? = null,
        @SerializedName("strStadiumLocation")
        val strStadiumLocation: String? = null,
        @SerializedName("strStadiumThumb")
        val strStadiumThumb: String? = null,
        @SerializedName("strTeam")
        val strTeam: String? = null,
        @SerializedName("strTeamBadge")
        val strTeamBadge: String? = null,
        @SerializedName("strTeamBanner")
        val strTeamBanner: String? = null,
        @SerializedName("strTeamFanart1")
        val strTeamFanart4: String? = null,
        @SerializedName("strTeamJersey")
        val strTeamJersey: String? = null,
        @SerializedName("strTeamLogo")
        val strTeamLogo: String? = null,
        @SerializedName("strTeamShort")
        val strTeamShort: Any,
        @SerializedName("strTwitter")
        val strTwitter: String? = null,
        @SerializedName("strWebsite")
        val strWebsite: String? = null,
        @SerializedName("strYoutube")
        val strYoutube: String? = null
    ) {
        fun toDomain() = TeamModel(
            idTeam = idTeam ?: "",
            strTeam = strTeam ?: "",
            strTeamBanner = strTeamBanner ?: "",
            strTeamBadge = strTeamBadge ?: "",
            strStadium = strStadium ?: "",
            strTeamJersey = strTeamJersey ?: "",
            strFacebook = (strFacebook ?: "").completeUrl(),
            strYoutube = (strYoutube ?: "").completeUrl(),
            strDescriptionES = strDescriptionES ?: "",
            intFormedYear = intFormedYear ?: ""
        )
    }
}
