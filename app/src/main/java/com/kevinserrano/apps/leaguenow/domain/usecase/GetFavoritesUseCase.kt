package com.kevinserrano.apps.leaguenow.domain.usecase


import com.kevinserrano.apps.leaguenow.domain.repository.FavoritesRepository
import javax.inject.Inject

/**
 * Created by Kevin Serrano 28/08/21
 */

class GetFavoritesUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
     fun run() = favoritesRepository.getFavorites()
}