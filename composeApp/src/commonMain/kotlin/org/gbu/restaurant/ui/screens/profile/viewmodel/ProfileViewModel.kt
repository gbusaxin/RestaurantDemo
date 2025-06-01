package org.gbu.restaurant.ui.screens.profile.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel

class ProfileViewModel(
//    private val getProfileUseCase: GetProfileUseCase
): BaseViewModel<ProfileEvent, ProfileState, Nothing>() {
    override fun setInitialState(): ProfileState = ProfileState()

    override fun onTriggerEvent(event: ProfileEvent) {
        when(event){
            is ProfileEvent.OnRetryNetwork -> TODO()
            is ProfileEvent.OnUpdateNetworkState -> TODO()
        }
    }
}