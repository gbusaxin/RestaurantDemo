package org.gbu.restaurant.decompose.bottomnavholder.home.notifications

import org.gbu.restaurant.ui.screens.notifications.viewmodel.NotificationsViewModel

interface NotificationsComponent {
    val viewModel: NotificationsViewModel
    fun popUp()
}