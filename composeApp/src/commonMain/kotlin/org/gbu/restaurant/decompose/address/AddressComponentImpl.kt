package org.gbu.restaurant.decompose.address

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.address.viewmodel.AddressViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddressComponentImpl(
    private val componentContext: ComponentContext,
    private val onNavigateToAddAddress: () -> Unit,
    private val onPopUp: () -> Unit
) : AddressComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: AddressViewModel by inject<AddressViewModel>()

    override fun navigateToAddAddress() {
        onNavigateToAddAddress()
    }

    override fun popUp() {
        onPopUp()
    }
}