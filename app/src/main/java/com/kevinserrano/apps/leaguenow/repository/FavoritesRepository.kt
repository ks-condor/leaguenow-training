package com.kevinserrano.apps.leaguenow.repository

import androidx.lifecycle.LiveData
import com.kevinserrano.apps.leaguenow.data.models.Team
import kotlinx.coroutines.flow.Flow


/**
 * Created by Kevin Serrano 28/08/21
 */
interface FavoritesRepository{

    fun getFavorites(): Flow<List<Team>>

    suspend fun insertFavorite(favorite: Team):Long

    suspend fun deleteFavorite(id:String)

    suspend fun existFavorite(id:String): Boolean

}