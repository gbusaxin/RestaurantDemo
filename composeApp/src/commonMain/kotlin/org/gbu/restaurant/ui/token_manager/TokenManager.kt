package org.gbu.restaurant.ui.token_manager

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.gbu.restaurant.business.data.network.common.DataState
import org.gbu.restaurant.business.usecase.CheckTokenUseCase
import org.gbu.restaurant.business.usecase.LogoutUseCase

class TokenManager(
    private val checkTokenUseCase: CheckTokenUseCase,
    private val logoutUseCase: LogoutUseCase
) {

    private val sessionScope = CoroutineScope(Dispatchers.Main)

    val state: MutableState<TokenState> = mutableStateOf(TokenState())

    fun onTriggerEvent(event: TokenEvent) {
        when (event) {
            TokenEvent.CheckToken -> {
                checkToken()
            }

            TokenEvent.Logout -> {
                logout()
            }
        }
    }

    private fun checkToken() {
        checkTokenUseCase.execute(Unit).onEach { dataState ->
            when (dataState) {
                is DataState.Data -> {
                    state.value = state.value.copy(isTokenAvailable = dataState.data ?: false)
                }

                is DataState.Loading<*> -> TODO()
                is DataState.NetworkStatus<*> -> TODO()
                is DataState.Response<*> -> TODO()
            }
        }.launchIn(sessionScope)
    }

    private fun logout() {
        logoutUseCase.execute(Unit).onEach { dataState ->
            when (dataState) {
                is DataState.Data -> {
                    checkToken()
                }

                is DataState.Loading<*> -> TODO()
                is DataState.NetworkStatus<*> -> TODO()
                is DataState.Response<*> -> TODO()
            }
        }.launchIn(sessionScope)
    }

}