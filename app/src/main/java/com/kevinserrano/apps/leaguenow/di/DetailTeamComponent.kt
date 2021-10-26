package com.kevinserrano.apps.leaguenow.di


import com.kevinserrano.apps.leaguenow.ui.activities.TeamDetailsActivity
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent(modules = [DetailTeamModule::class])
interface DetailTeamComponent {

    fun inject(teamDetailsActivity: TeamDetailsActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: TeamDetailsActivity): DetailTeamComponent
    }
    
}