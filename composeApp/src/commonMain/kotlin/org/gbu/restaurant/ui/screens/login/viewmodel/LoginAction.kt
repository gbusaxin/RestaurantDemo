package org.gbu.restaurant.ui.screens.login.viewmodel

import org.gbu.restaurant.business.core.ViewSingleAction

sealed class LoginAction: ViewSingleAction {

    sealed class Navigation: LoginAction() {
        data object NavigateToMain: Navigation()
        data object NavigateToLogin: Navigation()
    }

}