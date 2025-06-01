package org.gbu.restaurant.ui.screens.my_orders.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.local.entity.Order
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class MyOrdersState(
    val orders: List<Order> = listOf(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
) : ViewState
