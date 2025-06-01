package org.gbu.restaurant.ui.screens.wish_list.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent
import org.gbu.restaurant.business.data.local.entity.Category

sealed class WishListEvent : ViewEvent {
    data object GetNextPage : WishListEvent()
    data class OnUpdateSelectedCategory(val category: Category) : WishListEvent()
    data class LikeProduct(val id: Long) : WishListEvent()
    data object OnRetryNetwork : WishListEvent()
    data class OnUpdateNetworkState(val networkState: NetworkState) : WishListEvent()
}