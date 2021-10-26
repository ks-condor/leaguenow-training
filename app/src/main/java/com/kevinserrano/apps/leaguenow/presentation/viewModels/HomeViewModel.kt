package com.kevinserrano.apps.leaguenow.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevinserrano.apps.leaguenow.domain.models.TeamModel
import com.kevinserrano.apps.leaguenow.domain.usecase.GetFavoritesUseCase
import com.kevinserrano.apps.leaguenow.presentation.state.State
import com.kevinserrano.apps.leaguenow.domain.usecase.GetTeamsUseCase
import com.kevinserrano.apps.leaguenow.presentation.mapper.TeamMapper
import kotlinx.coroutines.launch

/**
 * Created by Kevin Serrano 28/08/21
 */
class HomeViewModel(private val getTeamsUseCase: GetTeamsUseCase,
                    private val getFavoritesUseCase: GetFavoritesUseCase) : ViewModel() {

    private val _stateGetTeams = MutableLiveData<State>()
    val stateGetTeams: LiveData<State> get() = _stateGetTeams
    private val _stateGetFavorites = MutableLiveData<State>()
    val stateGetFavorites: LiveData<State> get() = _stateGetFavorites

    init {
        getFavorites()
    }

    fun fetchTeams(idLeguea: String) {
        _stateGetTeams.value = State.Loading
        viewModelScope.launch {
            getTeamsUseCase.run(idLeguea).either(
                ::handleGetTeamsFailure,
                ::handleGetTeamsSuccess
            )
        }
    }

    private fun getFavorites(){
        viewModelScope.launch {
            getFavoritesUseCase.run().observeForever {
                val favorites = TeamMapper.fromDBToPresentation(it)
                _stateGetFavorites.value = if(favorites.isEmpty())
                    State.Empty
                else
                    State.Success(favorites)
            }
        }
    }

    private fun handleGetTeamsFailure(failure: Throwable) {
        _stateGetTeams.postValue(State.Failed(failure.localizedMessage ?: ""))
    }

    private fun handleGetTeamsSuccess(teams:List<TeamModel>) {
        if (teams.isNullOrEmpty()) _stateGetTeams.postValue(State.Empty)
        else
            _stateGetTeams.postValue(State.Success(teams))
    }
}