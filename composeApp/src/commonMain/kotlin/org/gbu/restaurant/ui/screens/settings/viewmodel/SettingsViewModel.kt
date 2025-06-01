package org.gbu.restaurant.ui.screens.settings.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.usecase.LogoutUseCase

class SettingsViewModel(
    private val logoutUseCase: LogoutUseCase
) : BaseViewModel<SettingsEvent, SettingsState, SettingsAction>() {
    override fun setInitialState(): SettingsState = SettingsState()

    override fun onTriggerEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.Logout -> {
                logout()
            }

            is SettingsEvent.OnRetryNetwork -> {

            }

            is SettingsEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    private fun logout() {
        executeUseCase(logoutUseCase.execute(Unit), onSuccess = {
            it?.let {
                setAction { SettingsAction.Navigation.PopUp }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {

        })
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }
}