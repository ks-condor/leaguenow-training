package com.kevinserrano.apps.leaguenow.data.repository

import com.kevinserrano.apps.leaguenow.data.models.Team
import com.kevinserrano.apps.leaguenow.data.local.FavoritesDao
import com.kevinserrano.apps.leaguenow.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Kevin Serrano 28/08/21
 */
class FavoritesRepositoryImpl @Inject constructor(private val favoritesDao: FavoritesDao) : FavoritesRepository {

    override fun getFavorites(): Flow<List<Team>> {
        return favoritesDao.getFavorites()
    }

    override suspend fun insertFavorite(favorite: Team):Long {
        return favoritesDao.insert(favorite)
    }

    override suspend fun deleteFavorite(id: String): Int {
        return favoritesDao.delete(id)
    }

    override suspend fun existFavorite(id: String): Boolean {
        return favoritesDao.existFavorite(id)!=null
    }

}