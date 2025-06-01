package org.gbu.restaurant.ui.states

import org.gbu.restaurant.business.data.local.entity.User

sealed interface LoginUIState {
    data object Idle : LoginUIState
    data object Loading : LoginUIState
    data class Authenticated(val user: User) : LoginUIState
    sealed class Error(val message: String) : LoginUIState {
        data class Phone(val cause: String) : Error(cause)
        data class Password(val cause: String) : Error(cause)
        data class Auth(val cause: String) : Error(cause)
    }
}