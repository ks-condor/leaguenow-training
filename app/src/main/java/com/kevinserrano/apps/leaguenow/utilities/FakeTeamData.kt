package com.kevinserrano.apps.leaguenow.utilities

import com.kevinserrano.apps.leaguenow.data.models.TeamsEntity
import com.kevinserrano.apps.leaguenow.presentation.models.TeamPresentation

object FakeTeamData {
    val teamsPresentation = listOf(
        TeamPresentation(
            idTeam = "134221",
            strTeam = "Alaves",
            strTeamBanner = "https://www.thesportsdb.com/images/media/team/banner/3axoi21504718301.jpg",
            strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/prle2v1596049676.png",
            strStadium = "Mendizorroza",
            strTeamJersey = "https://www.thesportsdb.com/images/media/team/jersey/uhk73q1626193253.png",
            strFacebook = "Facebook",
            strYoutube = "www.youtube.com/user/deportivoalaves1921",
            strDescriptionES = "El Deportivo Alavés, S.A.D es un club de fútbol español con sede en la ciudad de Vitoria. Fue fundado en el año 1921 y actualmente juega en la Segunda División de España. Su mayor éxito deportivo tuvo lugar en 2001, cuando, en el año de su debut en competición europea, fue finalista de la Copa de la UEFA ante el Liverpool FC, siendo derrotado por gol de oro (5-4).",
            intFormedYear = "1921"
        ),
        TeamPresentation(
            idTeam = "133727",
            strTeam = "Ath Bilbao",
            strTeamBanner = "https://www.thesportsdb.com/images/media/team/banner/hk0zvx1503237261.jpg",
            strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/1gs1c31549394822.png",
            strStadium = "San Mamés",
            strTeamJersey = "https://www.thesportsdb.com/images/media/team/jersey/xol5gs1614944826.png",
            strFacebook = "Facebook Real Madrid",
            strYoutube = "www.youtube.com/user/TVAthleticClub",
            strDescriptionES = "El Athletic Club es un club de fútbol de la villa de Bilbao, País Vasco, España. Fue fundado en 1898 y es, junto al Real Madrid Club de Fútbol y al Fútbol Club Barcelona, el único club que ha disputado todas las ediciones de la Primera División de España desde su creación en 1928. A su vez, es uno de los cuatro únicos clubes profesionales de España que no es una sociedad anónima deportiva, de manera que la propiedad del club recae en sus socios.7 8 Se lo conoce ampliamente como Athletic de Bilbao, y en algunas ocasiones con el nombre de la ciudad, Bilbao, o incluso como Atlético de Bilbao.9 10 Estos dos últimos términos resultan molestos para gran parte de los aficionados del club, ya que el primero no tiene ninguna relación con el nombre real del club, y el segundo porque fue el nombre impuesto al club durante la dictadura franquista.\\r\\n\\r\\nUna de las particularidades más representativas y originales del club vasco es su tradición de jugar únicamente con jugadores nacidos o formados",
            intFormedYear = "1898"
        ),
        TeamPresentation(
            idTeam = "1342281",
            strTeam = "Alaves Test",
            strTeamBanner = "https://www.thesportsdb.com/images/media/team/banner/3axoi21504718301.jpg",
            strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/prle2v1596049676.png",
            strStadium = "Mendizorroza",
            strTeamJersey = "https://www.thesportsdb.com/images/media/team/jersey/uhk73q1626193253.png",
            strFacebook = "Facebook",
            strYoutube = "www.youtube.com/user/deportivoalaves1921",
            strDescriptionES = "Description Alaves test",
            intFormedYear = "1921"
        ),
        TeamPresentation(
            idTeam = "5133727",
            strTeam = "Ath Bilbao Test",
            strTeamBanner = "https://www.thesportsdb.com/images/media/team/banner/hk0zvx1503237261.jpg",
            strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/1gs1c31549394822.png",
            strStadium = "San Mamés",
            strTeamJersey = "https://www.thesportsdb.com/images/media/team/jersey/xol5gs1614944826.png",
            strFacebook = "Facebook Real Madrid",
            strYoutube = "www.youtube.com/user/TVAthleticClub",
            strDescriptionES = "Description Ath Bilbao test",
            intFormedYear = "1898"
        )
    )

    val teamEntity = TeamsEntity(teams = listOf(
        TeamsEntity.Team(
            idTeam = "134221",
            strTeam = "Alaves",
            strTeamBanner = "https://www.thesportsdb.com/images/media/team/banner/3axoi21504718301.jpg",
            strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/prle2v1596049676.png",
            strStadium = "Mendizorroza",
            strTeamJersey = "https://www.thesportsdb.com/images/media/team/jersey/uhk73q1626193253.png",
            strFacebook = "Facebook",
            strYoutube = "www.youtube.com/user/deportivoalaves1921",
            strDescriptionES = "El Deportivo Alavés, S.A.D es un club de fútbol español con sede en la ciudad de Vitoria. Fue fundado en el año 1921 y actualmente juega en la Segunda División de España. Su mayor éxito deportivo tuvo lugar en 2001, cuando, en el año de su debut en competición europea, fue finalista de la Copa de la UEFA ante el Liverpool FC, siendo derrotado por gol de oro (5-4).",
            intFormedYear = "1921"
        ),
        TeamsEntity.Team(
            idTeam = "133727",
            strTeam = "Ath Bilbao",
            strTeamBanner = "https://www.thesportsdb.com/images/media/team/banner/hk0zvx1503237261.jpg",
            strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/1gs1c31549394822.png",
            strStadium = "San Mamés",
            strTeamJersey = "https://www.thesportsdb.com/images/media/team/jersey/xol5gs1614944826.png",
            strFacebook = "Facebook Real Madrid",
            strYoutube = "www.youtube.com/user/TVAthleticClub",
            strDescriptionES = "El Athletic Club es un club de fútbol de la villa de Bilbao, País Vasco, España. Fue fundado en 1898 y es, junto al Real Madrid Club de Fútbol y al Fútbol Club Barcelona, el único club que ha disputado todas las ediciones de la Primera División de España desde su creación en 1928. A su vez, es uno de los cuatro únicos clubes profesionales de España que no es una sociedad anónima deportiva, de manera que la propiedad del club recae en sus socios.7 8 Se lo conoce ampliamente como Athletic de Bilbao, y en algunas ocasiones con el nombre de la ciudad, Bilbao, o incluso como Atlético de Bilbao.9 10 Estos dos últimos términos resultan molestos para gran parte de los aficionados del club, ya que el primero no tiene ninguna relación con el nombre real del club, y el segundo porque fue el nombre impuesto al club durante la dictadura franquista.\\r\\n\\r\\nUna de las particularidades más representativas y originales del club vasco es su tradición de jugar únicamente con jugadores nacidos o formados",
            intFormedYear = "1898"
        ),
        TeamsEntity.Team(
            idTeam = "1342281",
            strTeam = "Alaves Test",
            strTeamBanner = "https://www.thesportsdb.com/images/media/team/banner/3axoi21504718301.jpg",
            strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/prle2v1596049676.png",
            strStadium = "Mendizorroza",
            strTeamJersey = "https://www.thesportsdb.com/images/media/team/jersey/uhk73q1626193253.png",
            strFacebook = "Facebook",
            strYoutube = "www.youtube.com/user/deportivoalaves1921",
            strDescriptionES = "Description Alaves test",
            intFormedYear = "1921"
        ),
        TeamsEntity.Team(
            idTeam = "5133727",
            strTeam = "Ath Bilbao Test",
            strTeamBanner = "https://www.thesportsdb.com/images/media/team/banner/hk0zvx1503237261.jpg",
            strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/1gs1c31549394822.png",
            strStadium = "San Mamés",
            strTeamJersey = "https://www.thesportsdb.com/images/media/team/jersey/xol5gs1614944826.png",
            strFacebook = "Facebook Real Madrid",
            strYoutube = "www.youtube.com/user/TVAthleticClub",
            strDescriptionES = "Description Ath Bilbao test",
            intFormedYear = "1898"
        )
    ))
}