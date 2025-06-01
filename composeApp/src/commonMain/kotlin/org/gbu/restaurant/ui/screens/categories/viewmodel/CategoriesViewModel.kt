package org.gbu.restaurant.ui.screens.categories.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.usecase.GetHomeUseCase

class CategoriesViewModel(
    private val getHomeUseCase: GetHomeUseCase
) : BaseViewModel<CategoriesEvent, CategoriesState, Nothing>() {
    override fun setInitialState(): CategoriesState = CategoriesState()

    override fun onTriggerEvent(event: CategoriesEvent) {
        when(event){
            is CategoriesEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }
            is CategoriesEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState()
            }
        }
    }

    init {
        getCategories()
    }

    private fun getCategories(){
        executeUseCase(getHomeUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(categories = it.categories) }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(CategoriesEvent.OnUpdateNetworkState(it))
        })
    }

    private fun onRetryNetwork(){
        getCategories()
    }

    private fun onUpdateNetworkState(){
        setState { copy(networkState = networkState) }
    }
}