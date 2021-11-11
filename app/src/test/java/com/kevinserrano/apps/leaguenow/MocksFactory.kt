package com.kevinserrano.apps.leaguenow


import com.kevinserrano.apps.leaguenow.data.models.Team
import com.kevinserrano.apps.leaguenow.data.models.TeamsEntity
import com.kevinserrano.apps.leaguenow.domain.models.TeamDomain
import com.kevinserrano.apps.leaguenow.presentation.models.TeamPresentation

object MocksFactory {
    val mockedTeamsDB = listOf(Team(
        idTeam = "",
        teamName = "",
        banner = "",
        badge = "",
        leagueName = "",
        stadium = "",
        teamJersey = "",
        facebook = "",
        youtube = "",
        description = "",
        formedYear = ""
    ))
    val mockedTeamsPresentation = listOf(TeamPresentation(
        idTeam = "GG666",
        strTeam = "Barcelona",
        strTeamBanner= "none",
        strTeamBadge = "None",
        strStadium = "Bernabeu",
        strTeamJersey = "....",
        strFacebook = "Facebook",
        strYoutube = "Youtube",
        strDescriptionES = "Equipo pro donde estaba Messi",
        intFormedYear = "1978"
    ))
    val mockedTeamPresentation = TeamPresentation(
        idTeam = "GG666",
        strTeam = "Barcelona",
        strTeamBanner= "none",
        strTeamBadge = "None",
        strStadium = "Bernabeu",
        strTeamJersey = "....",
        strFacebook = "Facebook",
        strYoutube = "Youtube",
        strDescriptionES = "Equipo pro donde estaba Messi",
        intFormedYear = "1978"
    )
    val mockedTeam = Team(
        idTeam = "",
        teamName = "Barcelona",
        banner = "none",
        badge = "none",
        leagueName = "HUHUH yy",
        stadium = "None Stadium",
        teamJersey = "Cally",
        facebook = "None",
        youtube = "None",
        description = "...none",
        formedYear = "1990"
    )
    val mockedTeamDomain = TeamDomain(
        idTeam = "GG666",
        strTeam = "Barcelona",
        strTeamBanner= "none",
        strTeamBadge = "None",
        strStadium = "Bernabeu",
        strTeamJersey = "....",
        strFacebook = "Facebook",
        strYoutube = "Youtube",
        strDescriptionES = "Equipo pro donde estaba Messi",
        intFormedYear = "1978"
    )
    val mockedTeamsEntity = TeamsEntity(teams = listOf(TeamsEntity.Team(
        idAPIfootball = "idAPIfootball",
        idLeague = "idLeague",
        idSoccerXML = "idSoccerXML",
        idTeam = "idTeam",
        intFormedYear = "intFormedYear",
        intLoved = Any(),
        intStadiumCapacity = "intStadiumCapacity",
        strAlternate = "strAlternate",
        strCountry = "strCountry",
        strDescriptionEN = "strDescriptionEN",
        strDescriptionES = "strDescriptionES",
        strDivision = Any(),
        strFacebook = "strFacebook",
        strGender = "strGender",
        strInstagram = "strInstagram",
        strKeywords = "strKeywords",
        strLeague = "strLeague",
        strLocked = "strLocked",
        strManager = "strManager",
        strRSS = "strRSS",
        strSport = "strSport",
        strStadium = "strStadium",
        strStadiumDescription = "strStadiumDescription",
        strStadiumLocation = "strStadiumLocation",
        strStadiumThumb = "strStadiumThumb",
        strTeam = "strTeam",
        strTeamBadge = "strTeamBadge",
        strTeamBanner = "strTeamBanner",
        strTeamFanart4 = "strTeamFanart4",
        strTeamJersey = "strTeamJersey",
        strTeamLogo = "strTeamLogo",
        strTeamShort = Any(),
        strTwitter = "strTwitter",
        strWebsite = "none",
        strYoutube = "Youtube"
    )))
    const val MOCKED_ID_LEAGUE = "4335"
    const val MOCKED_KEY_API = "1"
    const val MOCKED_ID_TEAM = "1090"
    const val MOCKED_NOT_ID_TEAM = "000000000"
}