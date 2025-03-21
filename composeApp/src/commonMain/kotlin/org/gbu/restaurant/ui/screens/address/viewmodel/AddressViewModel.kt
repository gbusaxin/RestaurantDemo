package org.gbu.restaurant.ui.screens.address.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.usecase.GetAddressesUseCase

class AddressViewModel(
    private val getAddressesUseCase: GetAddressesUseCase
) : BaseViewModel<AddressEvent, AddressState, Nothing>() {
    override fun setInitialState(): AddressState = AddressState()

    override fun onTriggerEvent(event: AddressEvent) {
        when (event) {
            is AddressEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is AddressEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getAddresses()
    }

    private fun getAddresses() {
        executeUseCase(getAddressesUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(addresses = it) }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(AddressEvent.OnUpdateNetworkState(it))
        })
    }

    private fun onRetryNetwork() {
        getAddresses()
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }
}