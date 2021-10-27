package com.kevinserrano.apps.leaguenow.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevinserrano.apps.leaguenow.domain.models.TeamModel
import com.kevinserrano.apps.leaguenow.domain.usecase.*
import com.kevinserrano.apps.leaguenow.presentation.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kevin Serrano 28/08/21
 */
@HiltViewModel
class DetailsTeamViewModel @Inject constructor(
    private val getTeamEventsUseCase: GetTeamEventsUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    //Observers objects
    private val _stateGetTeamEvents = MutableLiveData<State>()
    val stateGetTeamEvents: LiveData<State> get() = _stateGetTeamEvents
    private val _stateIsFavorite = MutableLiveData<Boolean>()
    val stateIsFavorite: LiveData<Boolean> get() = _stateIsFavorite
    private var isFavorite = false

    fun fetchTeamEvents(idTeam: String) {
        _stateGetTeamEvents.value = State.Loading
        viewModelScope.launch {
            getTeamEventsUseCase.run(idTeam).either(
                ::handleGetTeamsFailure,
                ::handleGetTeamsSuccess
            )
        }
    }

    fun isFavorite(idTeam: String) {
        viewModelScope.launch {
            isFavorite = isFavoriteUseCase.run(idTeam)
            _stateIsFavorite.postValue(isFavorite)
        }
    }

    fun favorite(team: TeamModel) {
        viewModelScope.launch {
            isFavorite = if (isFavorite) {
                deleteFavoriteUseCase.run(team.idTeam)
                false
            } else {
                val resultCode = insertFavoriteUseCase.run(team.toLocalData())
                if (resultCode >= 1L)
                    true
                else
                    isFavorite
            }
            _stateIsFavorite.postValue(isFavorite)
        }
    }

    private fun handleGetTeamsFailure(failure: Throwable) {
        _stateGetTeamEvents.postValue(State.Failed(failure.localizedMessage ?: ""))
    }

    private fun handleGetTeamsSuccess(teams: List<TeamModel>) {
        if (teams.isNullOrEmpty()) _stateGetTeamEvents.postValue(State.Empty)
        else
            _stateGetTeamEvents.postValue(State.Success(teams))
    }
}