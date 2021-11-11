package com.kevinserrano.apps.leaguenow.di

import com.kevinserrano.apps.leaguenow.domain.usecase.DeleteFavoriteUseCase
import com.kevinserrano.apps.leaguenow.domain.usecase.GetTeamEventsUseCase
import com.kevinserrano.apps.leaguenow.domain.usecase.InsertFavoriteUseCase
import com.kevinserrano.apps.leaguenow.domain.usecase.IsFavoriteUseCase
import com.kevinserrano.apps.leaguenow.domain.repository.FavoritesRepository
import com.kevinserrano.apps.leaguenow.domain.repository.TeamsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DetailTeamModule {

    // Domain
    @Provides
    fun providerDeleteFavoriteUserCase(favoritesRepository: FavoritesRepository): DeleteFavoriteUseCase{
        return DeleteFavoriteUseCase(favoritesRepository)
    }
    @Provides
    fun providerInsertFavoriteUserCase(favoritesRepository: FavoritesRepository): InsertFavoriteUseCase{
        return InsertFavoriteUseCase(favoritesRepository)
    }
    @Provides
    fun providerIsFavoriteUserCase(favoritesRepository: FavoritesRepository): IsFavoriteUseCase{
        return IsFavoriteUseCase(favoritesRepository)
    }
    @Provides
    fun providerGetTeamEventsUserCase(teamsRepository: TeamsRepository): GetTeamEventsUseCase{
        return GetTeamEventsUseCase(teamsRepository)
    }

}