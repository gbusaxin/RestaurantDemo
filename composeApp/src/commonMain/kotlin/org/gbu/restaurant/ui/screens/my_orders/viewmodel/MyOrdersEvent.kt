package org.gbu.restaurant.ui.screens.my_orders.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class MyOrdersEvent : ViewEvent {
    data object OnRetryNetwork : MyOrdersEvent()
    data class OnUpdateNetworkState(val networkState: NetworkState) : MyOrdersEvent()
}