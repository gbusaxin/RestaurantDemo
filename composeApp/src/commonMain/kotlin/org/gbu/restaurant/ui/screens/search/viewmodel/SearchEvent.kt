package org.gbu.restaurant.ui.screens.search.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.UIComponentState
import org.gbu.restaurant.business.core.ViewEvent
import org.gbu.restaurant.business.data.local.entity.Category

sealed class SearchEvent : ViewEvent {
    data class Search(
        val minPrice: Int? = null,
        val maxPrice: Int? = null,
        val categories: List<Category>? = null
    ) : SearchEvent()

    data object GetNextPage : SearchEvent()
    data class OnUpdateSelectedSort(val value: Int) : SearchEvent()
    data class OnUpdatePriceRange(val value: ClosedFloatingPointRange<Float>) : SearchEvent()
    data class OnUpdateSelectedCategory(val categories: List<Category>) : SearchEvent()
    data class OnUpdateSearchText(val value: String) : SearchEvent()
    data class OnUpdateSortDialogState(val value: UIComponentState) : SearchEvent()
    data class OnUpdateFilterDialogState(val value: UIComponentState) : SearchEvent()
    data object OnRetryNetwork : SearchEvent()
    data class OnUpdateNetworkState(val networkState: NetworkState) : SearchEvent()
}