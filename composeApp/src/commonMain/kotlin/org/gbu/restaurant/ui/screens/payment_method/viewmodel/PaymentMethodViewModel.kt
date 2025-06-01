package org.gbu.restaurant.ui.screens.payment_method.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState

class PaymentMethodViewModel : BaseViewModel<PaymentMethodEvent, PaymentMethodState, Nothing>() {
    override fun setInitialState(): PaymentMethodState = PaymentMethodState()

    override fun onTriggerEvent(event: PaymentMethodEvent) {
        when(event){
            is PaymentMethodEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }
            is PaymentMethodEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
            is PaymentMethodEvent.OnUpdateSelectedPaymentMethod -> {
                onUpdateSelectedPaymentMethod(event.value)
            }
        }
    }

    private fun onUpdateSelectedPaymentMethod(value: Int) {
        setState { copy(selectedPaymentMethod = value) }
    }

    private fun onRetryNetwork() {

    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }
}