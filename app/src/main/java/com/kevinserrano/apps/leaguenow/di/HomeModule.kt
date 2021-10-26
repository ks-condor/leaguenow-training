package com.kevinserrano.apps.leaguenow.di


import androidx.lifecycle.ViewModelProvider
import com.kevinserrano.apps.leaguenow.data.local.FavoritesDao
import com.kevinserrano.apps.leaguenow.data.remote.LeagueNowApi
import com.kevinserrano.apps.leaguenow.data.repository.FavoritesRepositoryImpl
import com.kevinserrano.apps.leaguenow.data.repository.TeamsRepositoryImpl
import com.kevinserrano.apps.leaguenow.domain.usecase.*
import com.kevinserrano.apps.leaguenow.presentation.viewModels.DetailsTeamViewModelFactory
import com.kevinserrano.apps.leaguenow.presentation.viewModels.HomeViewModelFactory
import com.kevinserrano.apps.leaguenow.repository.FavoritesRepository
import com.kevinserrano.apps.leaguenow.repository.TeamsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeModule {


    @Provides
    fun provideHomeViewModelFactory(getTeamsUseCase: GetTeamsUseCase,
                                    getFavoritesUseCase: GetFavoritesUseCase): ViewModelProvider.Factory{
        return HomeViewModelFactory(getTeamsUseCase, getFavoritesUseCase)
    }

    @Provides
    fun providerGetFavoritesUserCase(favoritesRepository: FavoritesRepository): GetFavoritesUseCase{
        return GetFavoritesUseCase(favoritesRepository)
    }
    @Provides
    fun providerGetTeamsUserCase(teamsRepository: TeamsRepository): GetTeamsUseCase{
        return GetTeamsUseCase(teamsRepository)
    }

}