package org.gbu.restaurant.ui.screens.detail.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class DetailEvent: ViewEvent {

    data class Like(val id: Long): DetailEvent()

    data class AddToBasket(val id: Long): DetailEvent()

    data class OnUpdateSelectedImage(val value: String): DetailEvent()

    data class GetProduct(val id: Long): DetailEvent()

    data object OnRetryNetwork: DetailEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState): DetailEvent()

}