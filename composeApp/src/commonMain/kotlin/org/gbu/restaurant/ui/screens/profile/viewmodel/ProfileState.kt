package org.gbu.restaurant.ui.screens.profile.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.local.entity.Profile
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class ProfileState(
    val profile: Profile = Profile(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
): ViewState {
}