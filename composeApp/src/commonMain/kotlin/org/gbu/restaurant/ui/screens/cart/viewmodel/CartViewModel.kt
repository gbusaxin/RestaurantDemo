package org.gbu.restaurant.ui.screens.cart.viewmodel

import org.gbu.restaurant.business.constants.CurrencyConstants
import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.usecase.CartListUseCase
import kotlin.math.round

class CartViewModel(
    private val cartListUseCase: CartListUseCase
) : BaseViewModel<CartEvent, CartState, Nothing>() {

    init {
        getCart()
    }

    override fun setInitialState(): CartState = CartState()

    override fun onTriggerEvent(event: CartEvent) {
        when (event) {
            is CartEvent.AddMenuItem -> {
                addProduct(event.id)
            }

//            is CartEvent.AddMoreProduct -> {
//
//            } // TODO delete if not implemented

            is CartEvent.DeleteFromCart -> {
                deleteFromCart(event.id)
            }

            is CartEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is CartEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    private fun getCart() {
        executeUseCase(cartListUseCase.execute(Unit),
            onSuccess = {
                it?.let {
                    setState { copy(cartItems = it) }
                    val totalCost =
                        state.value.cartItems.sumOf { cartItem -> cartItem.price.toDouble() }
                    val totalCostFormatted = formatPrice(totalCost)
                    setState { copy(totalCost = "$totalCostFormatted ${CurrencyConstants.CURRENCY}") }
                }
            },
            onLoading = {
                setState { copy(progressBarState = it) }
            },
            onNetworkStatus = {
                setEvent(CartEvent.OnUpdateNetworkState(it))
            })
    }

    private fun deleteFromCart(id: Long) {
//        executeUseCase()
    }

    private fun addProduct(id: Long) {
//        executeUseCase()
    }

    private fun onRetryNetwork() {
        getCart()
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }

    private fun formatPrice(value: Double): String {
        val rounded = round(value * 100) / 100  // Округляем до 2 знаков
        return rounded.toString().let {
            if (it.contains(".")) it.padEnd(it.indexOf(".") + 3, '0') else "$it.00"
        }
    }
}