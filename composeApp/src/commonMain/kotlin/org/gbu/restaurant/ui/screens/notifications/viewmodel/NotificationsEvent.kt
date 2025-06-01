package org.gbu.restaurant.ui.screens.notifications.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class NotificationsEvent : ViewEvent {
    data object OnRetryNetwork : NotificationsEvent()
    data class OnUpdateNetworkState(val networkState: NetworkState) : NotificationsEvent()
}