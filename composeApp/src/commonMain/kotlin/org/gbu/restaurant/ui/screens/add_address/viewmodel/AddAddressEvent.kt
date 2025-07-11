package org.gbu.restaurant.ui.screens.add_address.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class AddAddressEvent: ViewEvent {
    data class OnUpdateLatitude(val value: Double) : AddAddressEvent()

    data class OnUpdateLongitude(val value: Double) : AddAddressEvent()

    data class OnUpdateAddress(val value: String) : AddAddressEvent()

    data class OnUpdateState(val value: String) : AddAddressEvent()

    data class OnUpdateCountry(val value: String) : AddAddressEvent()

    data class OnUpdateZipCode(val value: String) : AddAddressEvent()

    data class OnUpdateCity(val value: String) : AddAddressEvent()


    data object AddAddress : AddAddressEvent()

    data object OnRetryNetwork : AddAddressEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : AddAddressEvent()
}