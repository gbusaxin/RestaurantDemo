package org.gbu.restaurant.ui.screens.login.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class LoginState(
    val nameRegister: String = "",
    val usernameLogin: String = "",
    val passwordLogin: String = "",

    val isTokenValid: Boolean = false,

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
) : ViewState