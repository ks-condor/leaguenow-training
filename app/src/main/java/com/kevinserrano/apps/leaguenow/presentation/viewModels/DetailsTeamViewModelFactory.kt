package com.kevinserrano.apps.leaguenow.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kevinserrano.apps.leaguenow.domain.usecase.*
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

class DetailsTeamViewModelFactory @Inject constructor(private val getTeamEventsUseCase: GetTeamEventsUseCase,
                                  private val isFavoriteUseCase: IsFavoriteUseCase,
                                  private val insertFavoriteUseCase: InsertFavoriteUseCase,
                                  private val deleteFavoriteUseCase: DeleteFavoriteUseCase
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsTeamViewModel::class.java)){
            return DetailsTeamViewModel(getTeamEventsUseCase, isFavoriteUseCase,
                insertFavoriteUseCase,deleteFavoriteUseCase) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}