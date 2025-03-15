package org.gbu.restaurant.decompose.menudetail

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.menudetail.viewmodel.MenuDetailsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MenuDetailComponentImpl(
    private val componentContext: ComponentContext,
    private val onBackClicked: () -> Unit,
    override val menuItemId: Long
) : MenuDetailComponent, ComponentContext by componentContext, KoinComponent {

    override val viewModel: MenuDetailsViewModel by inject<MenuDetailsViewModel>()

    override fun onBack() {
        onBackClicked()
    }
}