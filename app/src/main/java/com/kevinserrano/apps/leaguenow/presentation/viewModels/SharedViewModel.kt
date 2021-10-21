package com.kevinserrano.apps.leaguenow.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Kevin Serrano 28/08/21
 */
class SharedViewModel: ViewModel() {

    companion object {
        private var instance: SharedViewModel? = null
        fun getInstance() = instance ?: synchronized(SharedViewModel::class.java) {
            instance ?: SharedViewModel().also { instance = it }
        }
    }

    //Observers objects
    private val _filterTeams = MutableLiveData<String>()
    val filterTeams: LiveData<String> get() = _filterTeams


    fun filterTeams(idLeague:String){
        _filterTeams.value = idLeague
    }


}