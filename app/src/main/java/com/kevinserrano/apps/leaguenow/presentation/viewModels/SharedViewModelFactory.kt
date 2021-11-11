package com.kevinserrano.apps.leaguenow.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Created by Kevin Serrano 28/08/21
 */
class SharedViewModelFactory: ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(SharedViewModel::class.java) -> SharedViewModel.getInstance()
            else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
        }
    } as T

    companion object
    {
        private var instance: SharedViewModelFactory? = null
        fun getInstance() = instance ?: synchronized(SharedViewModelFactory::class.java) {
            instance ?: SharedViewModelFactory().also { instance = it }
        }
    }
}