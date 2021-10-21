package com.kevinserrano.apps.leaguenow.di


import com.kevinserrano.apps.leaguenow.BuildConfig
import com.kevinserrano.apps.leaguenow.BuildConfig.BASE_URL
import com.kevinserrano.apps.leaguenow.LeagueNowApp
import com.kevinserrano.apps.leaguenow.data.local.LeagueNowDatabase
import com.kevinserrano.apps.leaguenow.data.remote.LeagueNowApi
import com.kevinserrano.apps.leaguenow.data.repository.FavoritesRepositoryImpl
import com.kevinserrano.apps.leaguenow.data.repository.TeamsRepositoryImpl
import com.kevinserrano.apps.leaguenow.domain.usecase.*
import com.kevinserrano.apps.leaguenow.network.createNetworkClient
import com.kevinserrano.apps.leaguenow.presentation.viewModels.DetailsTeamViewModel
import com.kevinserrano.apps.leaguenow.repository.FavoritesRepository
import com.kevinserrano.apps.leaguenow.repository.TeamsRepository
import com.kevinserrano.apps.leaguenow.presentation.viewModels.TeamsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Kevin Serrano 28/08/21
 */
private const val RETROFIT_NAME = "LeagueNowApi"
private const val ROOM_NAME = "RoomDB"

// Presentation
val viewModelsModule: Module = module {
    viewModel {
        TeamsViewModel(
            getTeamsUseCase = get(),
            getFavoritesUseCase = get()
        )
    }
    viewModel {
        DetailsTeamViewModel(
            getTeamEventsUseCase = get(),
            isFavoriteUseCase = get(),
            insertFavoriteUseCase = get(),
            deleteFavoriteUseCase = get()
        )
    }
}

// Domain
val useCaseModel: Module = module {
    factory { GetTeamsUseCase(teamsRepository = get()) }
    factory { GetTeamEventsUseCase(teamsRepository = get()) }
    factory { GetFavoritesUseCase(favoritesRepository = get()) }
    factory { IsFavoriteUseCase(favoritesRepository = get()) }
    factory { InsertFavoriteUseCase(favoritesRepository = get()) }
    factory { DeleteFavoriteUseCase(favoritesRepository = get()) }
}

// Data
val repositoryModule: Module = module {
    single {
        TeamsRepositoryImpl(leagueNowApi = get()) as TeamsRepository
    }
    single {
        FavoritesRepositoryImpl(favoritesDao = get()) as FavoritesRepository
    }
}

// Network
val networkModule: Module = module {
    single(named(RETROFIT_NAME)) {
        createNetworkClient(
            baseUrl = BASE_URL,
            debug = BuildConfig.DEBUG
        )
    }

    single { get<Retrofit>(named(RETROFIT_NAME)).create(LeagueNowApi::class.java) }
}


// Local Database
val databaseModule: Module = module {
    single(named(ROOM_NAME)) {
        LeagueNowDatabase.getInstance(LeagueNowApp.app.applicationContext)
    }

    single { get<LeagueNowDatabase>(named(ROOM_NAME)).favoritesDao() }
}