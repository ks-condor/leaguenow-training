package com.kevinserrano.apps.leaguenow

import android.app.Application
import com.kevinserrano.apps.leaguenow.BuildConfig.DEBUG
import com.kevinserrano.apps.leaguenow.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

/**
 * Created by Kevin Serrano 28/08/21
 */
class LeagueNowApp : Application() {

    companion object {
        lateinit var app: LeagueNowApp
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            if (DEBUG) androidLogger()
            androidContext(this@LeagueNowApp)
            loadKoinModules(
                arrayListOf(
                    viewModelsModule,
                    useCaseModel,
                    repositoryModule,
                    networkModule,
                    databaseModule
                )
            )
        }
    }

}