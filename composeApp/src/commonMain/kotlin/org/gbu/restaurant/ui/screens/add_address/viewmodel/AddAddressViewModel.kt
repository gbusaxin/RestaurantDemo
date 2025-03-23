package org.gbu.restaurant.ui.screens.add_address.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.usecase.AddAddressUseCase

class AddAddressViewModel(
    private val addAddressUseCase: AddAddressUseCase
) : BaseViewModel<AddAddressEvent, AddAddressState, AddAddressAction>() {

    override fun setInitialState(): AddAddressState = AddAddressState()

    override fun onTriggerEvent(event: AddAddressEvent) {
        when (event) {
            is AddAddressEvent.AddAddress -> {
                addAddress()
            }

            is AddAddressEvent.OnRetryNetwork -> {
                // Empty because we do not load anything
                onRetryNetwork()
            }

            is AddAddressEvent.OnUpdateAddress -> {
                onUpdateAddress(event.value)
            }

            is AddAddressEvent.OnUpdateCity -> {
                onUpdateCity(event.value)
            }

            is AddAddressEvent.OnUpdateCountry -> {
                onUpdateCountry(event.value)
            }

            is AddAddressEvent.OnUpdateLatitude -> {
                onUpdateLatitude(event.value)
            }

            is AddAddressEvent.OnUpdateLongitude -> {
                onUpdateLongitude(event.value)
            }

            is AddAddressEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }

            is AddAddressEvent.OnUpdateState -> {
                onUpdateState(event.value)
            }

            is AddAddressEvent.OnUpdateZipCode -> {
                onUpdateZipCode(event.value)
            }
        }
    }

    private fun addAddress() {
        executeUseCase(addAddressUseCase.execute(
            AddAddressUseCase.Params(
                address = state.value.address,
                country = state.value.country,
                city = state.value.city,
                state = state.value.state,
                zipCode = state.value.zipCode,
            )
        ), onSuccess = {
            it?.let {
                if (it) setAction { AddAddressAction.Navigation.PopUp }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(AddAddressEvent.OnUpdateNetworkState(it))
        })
    }

    private fun onUpdateCity(value: String) {
        setState { copy(city = value) }
    }

    private fun onUpdateZipCode(value: String) {
        setState { copy(zipCode = value) }
    }

    private fun onUpdateState(value: String) {
        setState { copy(state = value) }
    }

    private fun onUpdateLongitude(value: Double) {
        setState { copy(longitude = value) }
    }

    private fun onUpdateLatitude(value: Double) {
        setState { copy(latitude = value) }
    }

    private fun onUpdateCountry(value: String) {
        setState { copy(country = value) }
    }

    private fun onUpdateAddress(value: String) {
        setState { copy(address = value) }
    }

    // Empty because we have nothing to download
    private fun onRetryNetwork() {}

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }
}