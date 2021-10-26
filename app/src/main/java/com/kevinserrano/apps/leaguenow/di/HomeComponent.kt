package com.kevinserrano.apps.leaguenow.di


import androidx.lifecycle.ViewModelProvider
import com.kevinserrano.apps.leaguenow.ui.activities.HomeActivity
import com.kevinserrano.apps.leaguenow.ui.fragments.HomeFragment
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    fun getTeamsComponentViewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

}