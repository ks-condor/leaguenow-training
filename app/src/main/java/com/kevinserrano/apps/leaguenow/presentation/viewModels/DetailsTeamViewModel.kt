package com.kevinserrano.apps.leaguenow.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevinserrano.apps.leaguenow.domain.models.TeamDomain
import com.kevinserrano.apps.leaguenow.presentation.models.TeamPresentation
import com.kevinserrano.apps.leaguenow.domain.usecase.*
import com.kevinserrano.apps.leaguenow.presentation.mapper.TeamMapper
import com.kevinserrano.apps.leaguenow.presentation.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val _stateGetTeamEvents = MutableStateFlow<State>(State.Loading)
    val stateGetTeamEvents: MutableStateFlow<State> get() = _stateGetTeamEvents
    private val _stateIsFavorite = MutableStateFlow(false)
    val stateIsFavorite: MutableStateFlow<Boolean> get() = _stateIsFavorite
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
            _stateIsFavorite.value = isFavorite
        }
    }

    fun favorite(teamPresentation: TeamPresentation) {
        viewModelScope.launch {
            isFavorite = if (isFavorite) {
                deleteFavoriteUseCase.run(teamPresentation.idTeam)
                false
            } else {
                val resultCode = insertFavoriteUseCase.run(TeamMapper.toDB(teamPresentation))
                if (resultCode >= 1L)
                    true
                else
                    isFavorite
            }
            _stateIsFavorite.value = isFavorite
        }
    }

    private fun handleGetTeamsFailure(failure: Throwable) {
        _stateGetTeamEvents.value = State.Failed(failure.localizedMessage ?: "")
    }

    private fun handleGetTeamsSuccess(teams: List<TeamDomain>) {
        if (teams.isNullOrEmpty()) _stateGetTeamEvents.value = State.Empty
        else
            _stateGetTeamEvents.value = State.Success(TeamMapper.fromDomainToPresentation(teams))
    }

}