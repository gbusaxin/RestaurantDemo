package org.gbu.restaurant.ui.screens.my_orders.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.usecase.GetOrdersUseCase

class MyOrdersViewModel(
    private val getOrdersUseCase: GetOrdersUseCase
) : BaseViewModel<MyOrdersEvent, MyOrdersState, Nothing>() {
    override fun setInitialState(): MyOrdersState = MyOrdersState()

    override fun onTriggerEvent(event: MyOrdersEvent) {
        when (event) {
            is MyOrdersEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is MyOrdersEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getOrders()
    }

    private fun getOrders() {
        executeUseCase(getOrdersUseCase.execute(Unit), onSuccess = {
            it?.let { setState { copy(orders = it) } }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(MyOrdersEvent.OnUpdateNetworkState(it))
        })
    }

    private fun onRetryNetwork() {
        getOrders()
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }
}