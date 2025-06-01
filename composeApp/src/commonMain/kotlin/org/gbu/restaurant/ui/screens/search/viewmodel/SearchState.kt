package org.gbu.restaurant.ui.screens.search.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.UIComponentState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.local.entity.Category
import org.gbu.restaurant.business.data.local.entity.Search
import org.gbu.restaurant.business.data.local.entity.SearchFilter
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class SearchState(
    val selectedCategory: List<Category> = listOf(),
    val selectedRange: ClosedFloatingPointRange<Float> = 0f..10f,
    val page: Int = 1,
    val hasNextPage: Boolean = true,
    val searchText: String = "",
    val searchFilter: SearchFilter = SearchFilter(),
    val search: Search = Search(),
    val selectedSort: Int = 0,
    val filterDialogState: UIComponentState = UIComponentState.Hide,
    val sortDialogState: UIComponentState = UIComponentState.Hide,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
):ViewState