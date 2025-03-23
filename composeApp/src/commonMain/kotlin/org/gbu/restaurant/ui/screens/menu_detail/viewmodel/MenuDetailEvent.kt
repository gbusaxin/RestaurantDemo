package org.gbu.restaurant.ui.screens.menu_detail.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class MenuDetailEvent() : ViewEvent {

    data class GetMenuItem(val itemId: Long) : MenuDetailEvent()

    data class AddToCart(val itemId: Long) : MenuDetailEvent()

    data object OnRetryNetwork: MenuDetailEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : MenuDetailEvent()

}