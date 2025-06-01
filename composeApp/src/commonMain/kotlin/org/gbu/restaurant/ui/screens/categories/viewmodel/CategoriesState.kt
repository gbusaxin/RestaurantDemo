package org.gbu.restaurant.ui.screens.categories.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.local.entity.Category
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class CategoriesState(
    val categories: List<Category> = emptyList(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
) : ViewState