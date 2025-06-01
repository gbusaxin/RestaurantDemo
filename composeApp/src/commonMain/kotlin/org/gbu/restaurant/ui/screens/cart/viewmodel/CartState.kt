package org.gbu.restaurant.ui.screens.cart.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.local.entity.CartItem
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class CartState(
    val cartItems: List<CartItem> = listOf(),
    val totalCost: String = "",
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
) : ViewState