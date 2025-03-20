package org.gbu.restaurant.ui.screens.cart.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class CartEvent : ViewEvent {
    data class DeleteFromCart(val id: Long) : CartEvent()

    data class AddMenuItem(val id: Long) : CartEvent()

//    data class AddMoreProduct(val menuItemId: Long): CartEvent()

    data object OnRetryNetwork : CartEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : CartEvent()
}