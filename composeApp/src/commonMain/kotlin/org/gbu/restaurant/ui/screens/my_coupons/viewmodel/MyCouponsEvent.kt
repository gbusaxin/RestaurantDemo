package org.gbu.restaurant.ui.screens.my_coupons.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class MyCouponsEvent : ViewEvent {
    data object OnRetryNetwork : MyCouponsEvent()
    data class OnUpdateNetworkState(val networkState: NetworkState) : MyCouponsEvent()
}