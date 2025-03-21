package org.gbu.restaurant.ui.screens.checkout.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.UIComponentState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.entity.Address
import org.gbu.restaurant.business.data.entity.ShippingType
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class CheckoutState(
    val totalCost: Double = 0.00,
    val totalCart: Double = 0.00,
    val addresses: List<Address> = listOf(),
    val selectedAddress: Address = Address(),
    val selectedShipping: ShippingType = shippingType_global.first(),
    val selectShippingDialogState: UIComponentState = UIComponentState.Hide,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
) : ViewState