package org.gbu.restaurant.ui.screens.splash.viewmodel

sealed interface SplashState {
    data object Waiting : SplashState
    data class Success(val onBoardBefore: Boolean) : SplashState
}