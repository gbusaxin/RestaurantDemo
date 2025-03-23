package org.gbu.restaurant.ui.screens.menu_detail.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.usecase.GetMenuDetailsUseCase

class MenuDetailsViewModel(
    private val getMenuDetailUseCase: GetMenuDetailsUseCase,
//    private val addMenuDetailToCartUseCase: AddMenuDetailToCartUseCase
) : BaseViewModel<MenuDetailEvent, MenuDetailState, Nothing>() {

    override fun setInitialState(): MenuDetailState = MenuDetailState()

    override fun onTriggerEvent(event: MenuDetailEvent) =
        when (event) {
            is MenuDetailEvent.AddToCart -> TODO()
            is MenuDetailEvent.GetMenuItem -> {
                getMenuDetail(event.itemId)
            }

            is MenuDetailEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is MenuDetailEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }

    private fun getMenuDetail(menuItemId: Long) {
        executeUseCase(getMenuDetailUseCase.execute(GetMenuDetailsUseCase.Params(menuItemId)),
            onSuccess = {
                it?.let {
                    setState {
                        copy(
                            menuItem = it
                        )
                    }
                }
            },
            onLoading = {
                setState { copy(progressBarState = it) }
            },
            onNetworkStatus = {
                setEvent(MenuDetailEvent.OnUpdateNetworkState(it))
            }
        )
    }

    private fun onRetryNetwork() {
        getMenuDetail(state.value.menuItem.id)
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }

}
