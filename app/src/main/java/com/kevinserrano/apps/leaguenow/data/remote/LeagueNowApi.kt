package com.kevinserrano.apps.leaguenow.data.remote

import retrofit2.http.*

/**
 * Created by Kevin Serrano 28/08/21
 */

interface LeagueNowApi {

    @GET("api/v1/json/{api_key}/lookup_all_teams.php")
    suspend fun getTeams(@Path("api_key") key: String,
                         @Query("id") idLeague: String): TeamsEntity

    @GET("api/v1/json/{api_key}/eventsnext.php")
    suspend fun getEvents(@Path("api_key") key: String,
                         @Query("id") idTeamm: String): TeamsEntity

}