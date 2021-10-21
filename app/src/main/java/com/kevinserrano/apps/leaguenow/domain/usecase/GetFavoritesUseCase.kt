package com.kevinserrano.apps.leaguenow.domain.usecase


import com.kevinserrano.apps.leaguenow.repository.FavoritesRepository

/**
 * Created by Kevin Serrano 28/08/21
 */

class GetFavoritesUseCase(private val favoritesRepository: FavoritesRepository) {
     fun run() = favoritesRepository.getFavorites()
}