package org.gbu.restaurant.ui.screens.categories.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class CategoriesEvent : ViewEvent {
    data object OnRetryNetwork : CategoriesEvent()
    data class OnUpdateNetworkState(val networkState: NetworkState) : CategoriesEvent()
}