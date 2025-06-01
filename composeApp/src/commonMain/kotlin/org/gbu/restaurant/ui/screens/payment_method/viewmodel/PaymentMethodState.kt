package org.gbu.restaurant.ui.screens.payment_method.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class PaymentMethodState(
    val selectedPaymentMethod: Int = 0,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
): ViewState {
}