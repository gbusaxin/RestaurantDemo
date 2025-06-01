package org.gbu.restaurant.ui.screens.payment_method.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class PaymentMethodEvent: ViewEvent {
    data class OnUpdateSelectedPaymentMethod(val value: Int):PaymentMethodEvent()
    data object OnRetryNetwork: PaymentMethodEvent()
    data class OnUpdateNetworkState(val networkState: NetworkState): PaymentMethodEvent()
}