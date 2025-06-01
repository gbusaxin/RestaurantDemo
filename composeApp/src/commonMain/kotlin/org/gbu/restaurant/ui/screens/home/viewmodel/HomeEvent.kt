package org.gbu.restaurant.ui.screens.home.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class HomeEvent : ViewEvent {
    data object OnRetryNetwork : HomeEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : HomeEvent()

    data class Like(val id: Long) : HomeEvent()
}