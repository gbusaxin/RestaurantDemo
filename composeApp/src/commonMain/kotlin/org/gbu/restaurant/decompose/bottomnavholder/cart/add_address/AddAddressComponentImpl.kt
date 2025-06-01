package org.gbu.restaurant.decompose.bottomnavholder.cart.add_address

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddAddressComponentImpl(
    private val componentContext: ComponentContext,
    private val onAddAddressInformation: () -> Unit,
    private val onPopUp: () -> Unit
): AddAddressComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: AddAddressViewModel by inject<AddAddressViewModel>()

    override fun addAddressInformation() {
        onAddAddressInformation()
    }

    override fun popUp() {
        onPopUp
    }
}