package com.kevinserrano.apps.leaguenow.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Kevin Serrano 28/08/21
 */
@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Team):Long

    @Query("DELETE FROM team WHERE idTeam = :idTeam")
    suspend fun delete(idTeam:String)

    @Query("SELECT * FROM team")
    fun getFavorites(): LiveData<List<Team>>

    @Query("SELECT * FROM team WHERE idTeam = :idTeam LIMIT 1")
    suspend fun existFavorite(idTeam:String): Team?

}