package org.gbu.restaurant.ui.screens.splash.viewmodel

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.gbu.restaurant.business.data.network.common.DataState
import org.gbu.restaurant.business.usecase.IsOnBoardedUseCase

class SplashViewModel(
    private val isOnBoardedUseCase: IsOnBoardedUseCase
) : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)
    private val _uiState = MutableStateFlow<SplashState>(SplashState.Waiting)
    val uiState: StateFlow<SplashState> = _uiState

    init {
        checkOnBoardingState()
    }

    private fun checkOnBoardingState() {
        viewModelScope.launch {
            delay(1500)
            isOnBoardedUseCase.execute(Unit).onEach { dataState ->
                when (dataState){
                    is DataState.Data -> {
                        _uiState.update {
                            SplashState.Success(onBoardBefore = dataState.data ?: false)
                        }
                    }
                    is DataState.Loading -> {
                    }
                    is DataState.NetworkStatus -> {
                    }
                    is DataState.Response -> {
                    }
                }
            }.launchIn(this)
        }
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }

}