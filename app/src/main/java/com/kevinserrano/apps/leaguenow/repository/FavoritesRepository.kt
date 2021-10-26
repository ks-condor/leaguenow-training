package com.kevinserrano.apps.leaguenow.repository

import androidx.lifecycle.LiveData
import com.kevinserrano.apps.leaguenow.data.local.Team
import javax.inject.Inject


/**
 * Created by Kevin Serrano 28/08/21
 */
interface FavoritesRepository{

    fun getFavorites(): LiveData<List<Team>>

    suspend fun insertFavorite(favorite: Team):Long

    suspend fun deleteFavorite(id:String)

    suspend fun existFavorite(id:String): Boolean

}