package org.gbu.restaurant.ui.screens.add_address.viewmodel

import org.gbu.restaurant.business.core.ViewSingleAction

sealed class AddAddressAction: ViewSingleAction {
    sealed class Navigation: AddAddressAction(){
        data object PopUp: Navigation()
    }
}