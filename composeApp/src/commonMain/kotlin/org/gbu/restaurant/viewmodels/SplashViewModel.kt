package org.gbu.restaurant.viewmodels

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.gbu.restaurant.ui.states.SplashUIState

class SplashViewModel : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)
    private val _uiState = MutableStateFlow<SplashUIState>(SplashUIState.Waiting)
    val uiState: StateFlow<SplashUIState> = _uiState

    init {
        checkOnBoardingState()
    }

    private fun checkOnBoardingState() {
        viewModelScope.launch {
            // TODO replace with actual implementation via Settings
            delay(1500)
            _uiState.update {
                SplashUIState.Success(onBoardBefore = false)
            }
        }
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }

}