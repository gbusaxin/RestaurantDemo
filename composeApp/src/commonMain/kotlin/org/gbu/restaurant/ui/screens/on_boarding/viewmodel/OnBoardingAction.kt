package org.gbu.restaurant.ui.screens.on_boarding.viewmodel

import org.gbu.restaurant.business.core.ViewSingleAction

sealed class OnBoardingAction: ViewSingleAction {
    sealed class Navigation: OnBoardingAction(){
        data object NavigateToSignInOptions: Navigation()
    }
}