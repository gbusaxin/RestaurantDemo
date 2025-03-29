package org.gbu.restaurant.ui.screens.signin_options.viewmodel

import org.gbu.restaurant.business.core.ViewSingleAction

sealed class SignInOptionsAction: ViewSingleAction {
    sealed class Navigation: SignInOptionsAction(){
        data object CreateAccount: Navigation()
        data object SignInRestaurant: Navigation()
        data object SignInGoogle: Navigation()
        data object SignInApple: Navigation()
    }
}