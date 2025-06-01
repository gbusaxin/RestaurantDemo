package org.gbu.restaurant.decompose.bottomnavholder.cart.add_address_info

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent

class AddAddressInfoComponentImpl(
    private val componentContext: ComponentContext,
    private val onPopUp: () -> Unit
): AddAddressInfoComponent, ComponentContext by componentContext, KoinComponent {
//    override val viewModel: AddAddressViewModel by inject<AddAddressViewModel>()

    override fun popUp() {
        onPopUp()
    }
}