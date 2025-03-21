package org.gbu.restaurant.ui.screens.address.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.entity.Address
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class AddressState(
    val addresses: List<Address> = listOf(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
) : ViewState