package org.gbu.restaurant.ui.states

sealed interface SplashUIState {

    data object Waiting : SplashUIState
    data class Success(val onBoardBefore: Boolean) : SplashUIState

}