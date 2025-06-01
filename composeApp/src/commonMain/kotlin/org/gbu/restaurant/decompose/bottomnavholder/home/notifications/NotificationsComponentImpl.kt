package org.gbu.restaurant.decompose.bottomnavholder.home.notifications

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.notifications.viewmodel.NotificationsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotificationsComponentImpl(
    componentContext: ComponentContext,
    private val onPopUp: () -> Unit
): NotificationsComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: NotificationsViewModel by inject<NotificationsViewModel>()

    override fun popUp() {
        onPopUp()
    }
}