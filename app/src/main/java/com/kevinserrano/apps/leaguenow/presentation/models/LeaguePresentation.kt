package com.kevinserrano.apps.leaguenow.presentation.models


import com.google.gson.annotations.SerializedName

/**
 * Created by Kevin Serrano 28/08/21
 */
data class LeaguePresentation(
    @SerializedName("idLeague")
    val idLeague: String = "",
    @SerializedName("strLeagueAlternate")
    val strLeagueAlternate: String = ""
)