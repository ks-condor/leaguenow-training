package com.kevinserrano.apps.leaguenow.di


import androidx.lifecycle.ViewModelProvider
import com.kevinserrano.apps.leaguenow.data.local.FavoritesDao
import com.kevinserrano.apps.leaguenow.data.remote.LeagueNowApi
import com.kevinserrano.apps.leaguenow.data.repository.FavoritesRepositoryImpl
import com.kevinserrano.apps.leaguenow.data.repository.TeamsRepositoryImpl
import com.kevinserrano.apps.leaguenow.domain.usecase.*
import com.kevinserrano.apps.leaguenow.presentation.viewModels.DetailsTeamViewModel
import com.kevinserrano.apps.leaguenow.presentation.viewModels.DetailsTeamViewModelFactory
import com.kevinserrano.apps.leaguenow.repository.FavoritesRepository
import com.kevinserrano.apps.leaguenow.repository.TeamsRepository
import com.kevinserrano.apps.leaguenow.ui.activities.TeamDetailsActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailTeamModule {

    @Provides
    fun provideDetailsTeamViewModel(activity: TeamDetailsActivity,
                                    getTeamEventsUseCase: GetTeamEventsUseCase,
                                           isFavoriteUseCase: IsFavoriteUseCase,
                                           insertFavoriteUseCase: InsertFavoriteUseCase,
                                           deleteFavoriteUseCase: DeleteFavoriteUseCase
    ): DetailsTeamViewModel {
        val factory = DetailsTeamViewModelFactory(getTeamEventsUseCase, isFavoriteUseCase,
            insertFavoriteUseCase, deleteFavoriteUseCase)
        return ViewModelProvider(activity,factory).get(DetailsTeamViewModel::class.java)
    }

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