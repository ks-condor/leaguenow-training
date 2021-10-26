package com.kevinserrano.apps.leaguenow.di

import android.content.Context
import androidx.room.Room
import com.kevinserrano.apps.leaguenow.BuildConfig
import com.kevinserrano.apps.leaguenow.data.local.FavoritesDao
import com.kevinserrano.apps.leaguenow.data.local.LeagueNowDatabase
import com.kevinserrano.apps.leaguenow.data.remote.LeagueNowApi
import com.kevinserrano.apps.leaguenow.data.repository.FavoritesRepositoryImpl
import com.kevinserrano.apps.leaguenow.data.repository.TeamsRepositoryImpl
import com.kevinserrano.apps.leaguenow.domain.usecase.*
import com.kevinserrano.apps.leaguenow.network.httpClient
import com.kevinserrano.apps.leaguenow.network.retrofitClient
import com.kevinserrano.apps.leaguenow.repository.FavoritesRepository
import com.kevinserrano.apps.leaguenow.repository.TeamsRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule {

    //Local Database
    @Provides
    @Singleton
    fun providesDatabase(context: Context): LeagueNowDatabase {
        return Room.databaseBuilder(
            context, LeagueNowDatabase::class.java,
            LeagueNowDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesFavoritesDao(database: LeagueNowDatabase): FavoritesDao {
        return database.favoritesDao()
    }


    //Network
    @Provides
    @Singleton
    fun provideAPIClient(): OkHttpClient {
        return httpClient(BuildConfig.DEBUG)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return retrofitClient(baseUrl = BuildConfig.BASE_URL,httpClient = okHttpClient)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(LeagueNowApi::class.java)

    @Provides
    fun provideTeamsRepository(leagueNowApi: LeagueNowApi): TeamsRepository {
        return TeamsRepositoryImpl(leagueNowApi)
    }

    @Provides
    fun providerFavoritesRepository(favoritesDao: FavoritesDao): FavoritesRepository {
        return FavoritesRepositoryImpl(favoritesDao)
    }

}