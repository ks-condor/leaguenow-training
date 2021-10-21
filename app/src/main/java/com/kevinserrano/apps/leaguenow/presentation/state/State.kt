package com.kevinserrano.apps.leaguenow.presentation.state

/**
 * Created by Kevin Serrano 28/08/21
 */
sealed class State {

    object Loading : State()
    object Empty : State()

    data class Failed(val failure: String) : State()
    data class Success(var data: Any) : State() {
        inline fun <reified T> responseTo() = data as T
    }
}
