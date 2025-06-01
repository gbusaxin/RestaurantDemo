package org.gbu.restaurant.ui.screens.detail.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.local.entity.Product
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class DetailState(
    val product: Product = Product(),
    val selectedImage: String = "",
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
) : ViewState
