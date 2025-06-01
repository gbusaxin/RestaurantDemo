package org.gbu.restaurant.decompose.bottomnavholder.home.settings

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.settings.viewmodel.SettingsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SettingsComponentImpl(
    componentContext: ComponentContext,
    private val onPopUp: () -> Unit
): SettingsComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: SettingsViewModel by inject<SettingsViewModel>()

    override fun popUp() {
        onPopUp()
    }
}