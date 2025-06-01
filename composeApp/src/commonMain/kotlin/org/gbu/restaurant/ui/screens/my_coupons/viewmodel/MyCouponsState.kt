package org.gbu.restaurant.ui.screens.my_coupons.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.local.entity.Coupon
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class MyCouponsState(
    val coupons: List<Coupon> = listOf(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
) : ViewState {
}