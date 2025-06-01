package org.gbu.restaurant.ui.screens.settings.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class SettingsEvent: ViewEvent {

    data object Logout: SettingsEvent()

    data object OnRetryNetwork: SettingsEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState): SettingsEvent()

}