package org.gbu.restaurant.ui.screens.checkout.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.UIComponentState
import org.gbu.restaurant.business.core.ViewEvent
import org.gbu.restaurant.business.data.local.entity.Address
import org.gbu.restaurant.business.data.local.entity.ShippingType

sealed class CheckoutEvent : ViewEvent {

    data class OnUpdateSelectedShipping(val value: ShippingType) : CheckoutEvent()

    data class OnUpdateSelectShippingDialogState(val value: UIComponentState) : CheckoutEvent()

    data class OnUpdateSelectedAddress(val value: Address) : CheckoutEvent()

    data object BuyProduct : CheckoutEvent()

    data object OnRetryNetwork : CheckoutEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CheckoutEvent()

}