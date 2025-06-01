package org.gbu.restaurant.decompose.bottomnavholder.home.settings

import org.gbu.restaurant.ui.screens.settings.viewmodel.SettingsViewModel

interface SettingsComponent {
    val viewModel: SettingsViewModel
    fun popUp()
}