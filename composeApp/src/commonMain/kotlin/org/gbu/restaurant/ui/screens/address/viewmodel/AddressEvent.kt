package org.gbu.restaurant.ui.screens.address.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class AddressEvent : ViewEvent {

    data object OnRetryNetwork : AddressEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : AddressEvent()

}