package org.gbu.restaurant.ui.screens.login.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.usecase.CheckTokenUseCase
import org.gbu.restaurant.business.usecase.LoginUseCase
import org.gbu.restaurant.business.usecase.RegisterUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val checkTokenUseCase: CheckTokenUseCase
) : BaseViewModel<LoginEvent, LoginState, LoginAction>() {
    override fun setInitialState(): LoginState = LoginState()

    override fun onTriggerEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> {
                login()
            }

            is LoginEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is LoginEvent.OnUpdateNameRegister -> {
                onUpdateNameRegister(event.value)
            }

            is LoginEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }

            is LoginEvent.OnUpdatePasswordLogin -> {
                onUpdatePasswordLogin(event.value)
            }

            is LoginEvent.OnUpdateUsernameLogin -> {
                onUpdateUsernameLogin(event.value)
            }

            is LoginEvent.Register -> {
                register()
            }
        }
    }

    init {
        checkToken()
    }

    private fun checkToken() {
        executeUseCase(checkTokenUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(isTokenValid = it) }

                setAction {
                    if (it) {
                        LoginAction.Navigation.NavigateToMain
                    } else {
                        LoginAction.Navigation.NavigateToLogin
                    }
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {})
    }

    private fun login() {
        executeUseCase(
            loginUseCase.execute(
                LoginUseCase.Params(
                    email = state.value.usernameLogin,
                    password = state.value.passwordLogin
                )
            ), onSuccess = {
                it?.let {
                    setAction {
                        if (it.isNotEmpty()) {
                            LoginAction.Navigation.NavigateToMain
                        } else {
                            LoginAction.Navigation.NavigateToLogin
                        }
                    }
                }
            }, onLoading = {
                setState { copy(progressBarState = it) }
            }, onNetworkStatus = {})
    }

    private fun register() {
        executeUseCase(
            registerUseCase.execute(
                RegisterUseCase.Params(
                    name = state.value.nameRegister,
                    email = state.value.usernameLogin,
                    password = state.value.passwordLogin
                )
            ), onSuccess = {
                it?.let {
                    setAction {
                        if (it.isNotEmpty()) {
                            LoginAction.Navigation.NavigateToMain
                        } else {
                            LoginAction.Navigation.NavigateToLogin
                        }
                    }
                }
            }, onLoading = {
                setState { copy(progressBarState = it) }
            }, onNetworkStatus = {})
    }

    private fun onUpdateNameRegister(value: String) {
        setState { copy(nameRegister = value) }
    }

    private fun onUpdatePasswordLogin(value: String) {
        setState { copy(passwordLogin = value) }
    }

    private fun onUpdateUsernameLogin(value: String) {
        setState { copy(usernameLogin = value) }
    }

    private fun onRetryNetwork() {

    }

    private fun onUpdateNetworkState(value: NetworkState) {
        setState { copy(networkState = value) }
    }
}











