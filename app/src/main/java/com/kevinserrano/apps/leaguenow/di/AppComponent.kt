package com.kevinserrano.apps.leaguenow.di

import android.content.Context
import com.kevinserrano.apps.leaguenow.ui.activities.TeamDetailsActivity
import com.kevinserrano.apps.leaguenow.ui.fragments.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,SubComponents::class])
interface AppComponent {

    fun inject(home:HomeFragment)

    fun getTeamsComponent(): HomeComponent.Factory

    fun getDetailTeamComponent(): DetailTeamComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}