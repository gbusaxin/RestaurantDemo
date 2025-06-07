package org.gbu.restaurant.ui.screens.login.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewEvent

sealed class LoginEvent : ViewEvent {
    data class OnUpdateNameRegister(val value: String) : LoginEvent()
    data class OnUpdateUsernameLogin(val value: String) : LoginEvent()
    data class OnUpdatePasswordLogin(val value: String) : LoginEvent()

    data object Register : LoginEvent()
    data object Login : LoginEvent()

    data object OnRetryNetwork : LoginEvent()
    data class OnUpdateNetworkState(val networkState: NetworkState) : LoginEvent()
}