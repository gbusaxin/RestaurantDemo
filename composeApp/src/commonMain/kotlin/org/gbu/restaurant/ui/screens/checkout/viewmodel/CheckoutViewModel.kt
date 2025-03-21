package org.gbu.restaurant.ui.screens.checkout.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.UIComponentState
import org.gbu.restaurant.business.data.entity.Address
import org.gbu.restaurant.business.data.entity.ShippingType
import org.gbu.restaurant.business.usecase.BuyProductUseCase
import org.gbu.restaurant.business.usecase.CartListUseCase
import org.gbu.restaurant.business.usecase.GetAddressesUseCase

val shippingType_global = listOf(
    ShippingType("Bolt Food", 4.30, 1),
    ShippingType("Wolt Delivery", 5.00, 1),
    ShippingType("Uber Eats", 5.10, 1)
)

class CheckoutViewModel(
    private val cartListUseCase: CartListUseCase,
    private val getAddressesUseCase: GetAddressesUseCase,
    private val buyProductUseCase: BuyProductUseCase
) : BaseViewModel<CheckoutEvent, CheckoutState, CheckoutAction>() {

    init {
        getAddresses()
        getCart()
    }

    override fun setInitialState(): CheckoutState = CheckoutState()

    override fun onTriggerEvent(event: CheckoutEvent) {
        when (event) {
            is CheckoutEvent.BuyProduct -> {
                buyProduct()
            }

            is CheckoutEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is CheckoutEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }

            is CheckoutEvent.OnUpdateSelectShippingDialogState -> {
                onUpdateSelectShippingDialogState(event.value)
            }

            is CheckoutEvent.OnUpdateSelectedAddress -> {
                onUpdateSelectedAddress(event.value)
            }

            is CheckoutEvent.OnUpdateSelectedShipping -> {
                onUpdateSelectedShipping(event.value)
            }
        }
    }

    private fun buyProduct() {
        executeUseCase(buyProductUseCase.execute(
            BuyProductUseCase.Params(
                addressId = state.value.selectedAddress.id,
                shippingType = shippingType_global.indexOf(state.value.selectedShipping)
            )
        ), onSuccess = {
            it?.let {
                if (it) {
                    setAction { CheckoutAction.Navigation.PopUp }
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(CheckoutEvent.OnUpdateNetworkState(it))
        })
    }

    private fun getCart() {
        executeUseCase(cartListUseCase.execute(Unit), onSuccess = {
            it?.let {
                val totalCost = it.sumOf { cart ->
                    cart.price.toDouble()
                }
                setState {
                    copy(
                        totalCart = totalCost,
                        totalCost = totalCost + state.value.selectedShipping.price
                    )
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(CheckoutEvent.OnUpdateNetworkState(it))
        })
    }

    private fun getAddresses() {
        executeUseCase(getAddressesUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(addresses = it) }
                it.firstOrNull()?.let { address ->
                    setState { copy(selectedAddress = address) }
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(CheckoutEvent.OnUpdateNetworkState(it))
        })
    }

    private fun onUpdateSelectShippingDialogState(value: UIComponentState) {
        setState {
            copy(
                selectShippingDialogState = value,
                totalCost = state.value.totalCart + state.value.selectedShipping.price
            )
        }
    }

    private fun onUpdateSelectedShipping(value: ShippingType) {
        setState { copy(selectedShipping = value) }
    }

    private fun onUpdateSelectedAddress(value: Address) {
        setState { copy(selectedAddress = value) }
    }

    private fun onRetryNetwork() {
        getAddresses()
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }
}