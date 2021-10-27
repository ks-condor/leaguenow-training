package com.kevinserrano.apps.leaguenow

import android.app.Application
import com.kevinserrano.apps.leaguenow.di.*
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Kevin Serrano 28/08/21
 */
@HiltAndroidApp
class LeagueNowApp : Application() {

    companion object {
        lateinit var app: LeagueNowApp
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

}