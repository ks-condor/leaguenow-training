package com.kevinserrano.apps.leaguenow.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevinserrano.apps.leaguenow.domain.models.TeamDomain
import com.kevinserrano.apps.leaguenow.presentation.models.TeamPresentation
import com.kevinserrano.apps.leaguenow.domain.usecase.GetFavoritesUseCase
import com.kevinserrano.apps.leaguenow.presentation.state.State
import com.kevinserrano.apps.leaguenow.domain.usecase.GetTeamsUseCase
import com.kevinserrano.apps.leaguenow.presentation.mapper.TeamMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kevin Serrano 28/08/21
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val getTeamsUseCase: GetTeamsUseCase,
                    private val getFavoritesUseCase: GetFavoritesUseCase) : ViewModel() {

    private val _stateGetTeams = MutableStateFlow<State>(State.Loading)
    val stateGetTeams: StateFlow<State> get() = _stateGetTeams
    private val _stateGetFavorites = MutableStateFlow<State>(State.Empty)
    val stateGetFavorites: StateFlow<State> get() = _stateGetFavorites

    init {
        getFavorites()
    }

    fun fetchTeams(idLeguea: String) {
        viewModelScope.launch {
            getTeamsUseCase.run(idLeguea).either(
                ::handleGetTeamsFailure,
                ::handleGetTeamsSuccess
            )
        }
    }

    private fun getFavorites(){
        viewModelScope.launch {
            getFavoritesUseCase.run().collect {
                val favorites = TeamMapper.fromDBToPresentation(it)
                _stateGetFavorites.value = if(favorites.isEmpty())
                    State.Empty
                else
                    State.Success(favorites)
            }
        }
    }

    private fun handleGetTeamsFailure(failure: Throwable) {
        _stateGetTeams.value = State.Failed(failure.localizedMessage ?: "")
    }

    private fun handleGetTeamsSuccess(teams:List<TeamDomain>) {
        _stateGetTeams.value = if (teams.isNullOrEmpty()) State.Empty
        else
            State.Success(TeamMapper.fromDomainToPresentation(teams))
    }
}