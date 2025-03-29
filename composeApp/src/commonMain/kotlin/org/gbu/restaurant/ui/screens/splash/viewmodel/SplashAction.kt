package org.gbu.restaurant.ui.screens.splash.viewmodel

import org.gbu.restaurant.business.core.ViewSingleAction

sealed class SplashAction: ViewSingleAction {
    sealed class Navigation: SplashAction(){
        data object NavigateToOnBoarding: Navigation()
        data object NavigateToSignInOptions: Navigation()
    }
}