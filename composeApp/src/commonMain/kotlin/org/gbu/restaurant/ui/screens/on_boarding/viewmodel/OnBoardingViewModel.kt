package org.gbu.restaurant.ui.screens.on_boarding.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.usecase.SetOnBoardingUseCase

class OnBoardingViewModel(
    private val setOnBoardingUseCase: SetOnBoardingUseCase
) : BaseViewModel<OnBoardingEvent, OnBoardingState, OnBoardingAction>() {
    override fun setInitialState(): OnBoardingState {
        return OnBoardingState()
    }

    override fun onTriggerEvent(event: OnBoardingEvent) {
        when (event) {
            OnBoardingEvent.GetStarted -> {
                signInOptions()
            }
        }
    }

    private fun signInOptions() {
        executeUseCase(setOnBoardingUseCase.execute(true), onSuccess = {
            setAction { OnBoardingAction.Navigation.NavigateToSignInOptions }
        }, onLoading = {

        }, onNetworkStatus = {

        })
    }
}