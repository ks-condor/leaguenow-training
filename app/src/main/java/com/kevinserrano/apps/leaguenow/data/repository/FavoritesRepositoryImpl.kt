package com.kevinserrano.apps.leaguenow.data.repository

import androidx.lifecycle.LiveData
import com.kevinserrano.apps.leaguenow.data.models.Team
import com.kevinserrano.apps.leaguenow.data.local.FavoritesDao
import com.kevinserrano.apps.leaguenow.repository.FavoritesRepository
import javax.inject.Inject


/**
 * Created by Kevin Serrano 28/08/21
 */
class FavoritesRepositoryImpl @Inject constructor(private val favoritesDao: FavoritesDao) : FavoritesRepository {

    override fun getFavorites(): LiveData<List<Team>> {
        return favoritesDao.getFavorites()
    }

    override suspend fun insertFavorite(favorite: Team):Long {
        return favoritesDao.insert(favorite)
    }

    override suspend fun deleteFavorite(id: String) {
        return favoritesDao.delete(id)
    }

    override suspend fun existFavorite(id: String): Boolean {
        return favoritesDao.existFavorite(id)!=null
    }

}