package com.kevinserrano.apps.leaguenow.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kevinserrano.apps.leaguenow.domain.usecase.GetFavoritesUseCase
import com.kevinserrano.apps.leaguenow.domain.usecase.GetTeamsUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

class HomeViewModelFactory @Inject constructor(private val getTeamsUseCase: GetTeamsUseCase,
                           private val getFavoritesUseCase: GetFavoritesUseCase
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(getTeamsUseCase, getFavoritesUseCase) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}