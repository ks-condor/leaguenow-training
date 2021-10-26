package com.kevinserrano.apps.leaguenow

import android.app.Application
import com.kevinserrano.apps.leaguenow.di.*


/**
 * Created by Kevin Serrano 28/08/21
 */
class LeagueNowApp : Application() {

    companion object {
        lateinit var app: LeagueNowApp
    }

    lateinit var  appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
        app = this
    }

}