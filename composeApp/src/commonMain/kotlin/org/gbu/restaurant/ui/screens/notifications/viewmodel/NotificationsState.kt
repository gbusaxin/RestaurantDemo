package org.gbu.restaurant.ui.screens.notifications.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.local.entity.Notification
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class NotificationsState(
    val notifications: List<Notification> = listOf(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
): ViewState