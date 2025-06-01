package org.gbu.restaurant.ui.screens.detail.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState

class DetailViewModel(
//    private val productUseCase: ProductUseCase,
//    private val addToBasketUseCase: AddToBasketUseCase,
//    private val likeUseCase: LikeUseCase
): BaseViewModel<DetailEvent, DetailState, Nothing>() {
    override fun setInitialState(): DetailState = DetailState()

    override fun onTriggerEvent(event: DetailEvent) {
        when(event){
            is DetailEvent.AddToBasket -> {
                addToBasket(event.id)
            }
            is DetailEvent.GetProduct -> {
                getProduct(event.id)
            }
            is DetailEvent.Like -> {
                likeProduct(event.id)
            }
            is DetailEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }
            is DetailEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
            is DetailEvent.OnUpdateSelectedImage -> {
                onUpdateSelectedImage(event.value)
            }
        }
    }

    private fun onUpdateSelectedImage(value: String){
        setState { copy(selectedImage = value) }
    }

    private fun likeProduct(id: Long){
        TODO()
    }

    private fun addToBasket(id: Long){
        TODO()
    }

    private fun updateLike(){
        setState { copy(product = state.value.product.copy(isLike = !state.value.product.isLike)) }
    }

    private fun getProduct(id: Long){
        TODO()
    }

    private fun onRetryNetwork(){
        getProduct(state.value.product.id)
    }

    private fun onUpdateNetworkState(networkState: NetworkState){
        setState { copy(networkState = networkState) }
    }
}