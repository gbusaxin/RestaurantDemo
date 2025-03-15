package org.gbu.restaurant.business.core

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.gbu.restaurant.business.data.network.common.DataState
import org.gbu.restaurant.business.data.network.common.ProgressBarState

abstract class BaseViewModel<
        Event : ViewEvent,
        UiState : ViewState,
        SingleAction : ViewSingleAction
        > : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)

    abstract fun setInitialState(): UiState
    abstract fun onTriggerEvent(event: Event)

    private val initialState: UiState by lazy { setInitialState() }
    private val _state: MutableState<UiState> = mutableStateOf(initialState)
    val state = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    private val _action: Channel<SingleAction> = Channel()
    val action = _action

    private val _errors: Channel<UIComponent> = Channel()
    val errors = _errors.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                onTriggerEvent(it)
            }
        }
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = state.value.reducer()
        _state.value = newState
    }

    protected fun setAction(builder: () -> SingleAction) {
        val effectiveValue = builder()
        viewModelScope.launch { _action.send(effectiveValue) }
    }

    protected fun setError(builder: () -> UIComponent) {
        val effectValue = builder()
        viewModelScope.launch {
            _errors.send(effectValue)
        }
    }

    fun <T> executeUseCase(
        flow: Flow<DataState<T>>,
        onSuccess: (T?) -> Unit,
        onLoading: (ProgressBarState) -> Unit,
        onNetworkStatus: (NetworkState) -> Unit
    ) {
        viewModelScope.launch {
            flow.collectLatest { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> onNetworkStatus(dataState.networkState)
                    is DataState.Response -> setError { dataState.uiComponent }
                    is DataState.Data -> onSuccess(dataState.data)
                    is DataState.Loading -> onLoading(dataState.progressBarState)
                }
            }
        }
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }

}