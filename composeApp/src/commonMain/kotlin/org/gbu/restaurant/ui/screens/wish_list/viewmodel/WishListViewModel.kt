package org.gbu.restaurant.ui.screens.wish_list.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel

class WishListViewModel(
//    private val wishListUseCase: WishListUseCase,
//    private val likeUseCase: LikeUseCase
): BaseViewModel<WishListEvent, WishListState, Nothing>() {
    override fun setInitialState(): WishListState = WishListState()

    override fun onTriggerEvent(event: WishListEvent) {
        when(event){
            is WishListEvent.GetNextPage -> TODO()
            is WishListEvent.LikeProduct -> TODO()
            is WishListEvent.OnRetryNetwork -> TODO()
            is WishListEvent.OnUpdateNetworkState -> TODO()
            is WishListEvent.OnUpdateSelectedCategory -> TODO()
        }
    }
}