package org.gbu.restaurant.ui.screens.home.viewmodel

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.usecase.GetHomeUseCase

class HomeViewModel(
    private val homeUseCase: GetHomeUseCase,
//    private val likeUseCase: LikeUseCase TODO()
) : BaseViewModel<HomeEvent, HomeState, Nothing>() {
    override fun setInitialState(): HomeState = HomeState()

    override fun onTriggerEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Like -> {
                likeProduct(event.id)
            }

            is HomeEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is HomeEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getHome()
    }

    private fun likeProduct(id: Long) {
        TODO("Implement it")
    }

    private fun updateLike(id: Long) {
        TODO("Implement it")
    }

    private fun updateFlashSaleProductLike(id: Long) {
        TODO("Implement it")
    }

    private fun updateNewestProductLike(id: Long) {
        TODO("Implement it")
    }

    private fun updateMostSaleProductLike(id: Long) {
        TODO("Implement it")
    }

    private fun getHome() {
        executeUseCase(homeUseCase.execute(Unit), onSuccess = {
            it?.let {
                val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
//                    Instant.parse(it.flashSale.expiredAt).toLocalDateTime(TimeZone.UTC) // TODO()
                setState { copy(home = it, time = currentDateTime) }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(HomeEvent.OnUpdateNetworkState(it))
        })
    }

    private fun onRetryNetwork() {
        getHome()
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }
}