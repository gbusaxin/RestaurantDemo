package org.gbu.restaurant.decompose.add_address

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddAddressInfoComponentImpl(
    private val componentContext: ComponentContext,
    private val onPopUp: () -> Unit
): AddAddressInfoComponent, ComponentContext by componentContext, KoinComponent {
//    override val viewModel: AddAddressViewModel by inject<AddAddressViewModel>()

    override fun popUp() {
        onPopUp()
    }
}