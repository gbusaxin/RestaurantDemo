package org.gbu.restaurant.ui.screens.settings.viewmodel

import org.gbu.restaurant.business.core.ViewSingleAction

sealed class SettingsAction: ViewSingleAction {
    sealed class Navigation: SettingsAction(){
        data object PopUp: Navigation()
    }
}