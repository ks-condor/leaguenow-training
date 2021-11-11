package com.kevinserrano.apps.leaguenow.di


import com.kevinserrano.apps.leaguenow.domain.usecase.*
import com.kevinserrano.apps.leaguenow.domain.repository.FavoritesRepository
import com.kevinserrano.apps.leaguenow.domain.repository.TeamsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HomeModule {

    @Provides
    fun providerGetFavoritesUserCase(favoritesRepository: FavoritesRepository): GetFavoritesUseCase{
        return GetFavoritesUseCase(favoritesRepository)
    }
    @Provides
    fun providerGetTeamsUserCase(teamsRepository: TeamsRepository): GetTeamsUseCase{
        return GetTeamsUseCase(teamsRepository)
    }

}