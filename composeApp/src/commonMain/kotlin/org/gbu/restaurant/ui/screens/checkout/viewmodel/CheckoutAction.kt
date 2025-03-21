package org.gbu.restaurant.ui.screens.checkout.viewmodel

import org.gbu.restaurant.business.core.ViewSingleAction

sealed class CheckoutAction: ViewSingleAction {

    sealed class Navigation: CheckoutAction(){
        data object PopUp: Navigation()
    }

}