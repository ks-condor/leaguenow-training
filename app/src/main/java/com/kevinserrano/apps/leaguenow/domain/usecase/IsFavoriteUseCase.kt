package com.kevinserrano.apps.leaguenow.domain.usecase


import com.kevinserrano.apps.leaguenow.repository.FavoritesRepository

/**
 * Created by Kevin Serrano 28/08/21
 */

class IsFavoriteUseCase(private val favoritesRepository: FavoritesRepository) {
    suspend fun run(idTeam: String) = favoritesRepository.existFavorite(idTeam)
}