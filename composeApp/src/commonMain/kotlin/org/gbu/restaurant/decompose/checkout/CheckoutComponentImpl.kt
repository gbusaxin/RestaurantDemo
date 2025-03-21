package org.gbu.restaurant.decompose.checkout

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.checkout.viewmodel.CheckoutViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CheckoutComponentImpl(
    private val componentContext: ComponentContext,
    private val onPopUp: () -> Unit,
    private val onNavigateToAddress: () -> Unit
) : CheckoutComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: CheckoutViewModel by inject<CheckoutViewModel>()

    override fun popUp() {
        onPopUp()
    }

    override fun navigateToAddress() {
        onNavigateToAddress()
    }
}