package org.gbu.restaurant.ui.screens.profile.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class ProfileEvent : ViewEvent {
    data object OnRetryNetwork : ProfileEvent()
    data class OnUpdateNetworkState(val networkState: NetworkState) : ProfileEvent()
}