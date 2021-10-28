package com.kevinserrano.apps.leaguenow.domain.usecase


import com.kevinserrano.apps.leaguenow.data.models.Team
import com.kevinserrano.apps.leaguenow.repository.FavoritesRepository
import javax.inject.Inject

/**
 * Created by Kevin Serrano 28/08/21
 */

class InsertFavoriteUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    suspend fun run(favorite: Team) = favoritesRepository.insertFavorite(favorite)
}