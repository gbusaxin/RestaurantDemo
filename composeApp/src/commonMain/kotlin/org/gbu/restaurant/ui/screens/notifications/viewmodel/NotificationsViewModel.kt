package org.gbu.restaurant.ui.screens.notifications.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel

class NotificationsViewModel(
//    private val getNotificationsUseCase: GetNotificationsUseCase,
): BaseViewModel<NotificationsEvent, NotificationsState, Nothing>() {
    override fun setInitialState(): NotificationsState = NotificationsState()

    override fun onTriggerEvent(event: NotificationsEvent) {
        when(event){
            is NotificationsEvent.OnRetryNetwork -> TODO()
            is NotificationsEvent.OnUpdateNetworkState -> TODO()
        }
    }
}