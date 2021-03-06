package com.kevinserrano.apps.leaguenow.domain.usecase


import com.kevinserrano.apps.leaguenow.data.local.Team
import com.kevinserrano.apps.leaguenow.repository.FavoritesRepository

/**
 * Created by Kevin Serrano 28/08/21
 */

class InsertFavoriteUseCase(private val favoritesRepository: FavoritesRepository) {
    suspend fun run(favorite: Team) = favoritesRepository.insertFavorite(favorite)
}