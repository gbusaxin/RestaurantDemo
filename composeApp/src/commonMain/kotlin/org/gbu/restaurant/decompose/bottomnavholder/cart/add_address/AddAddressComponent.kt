package org.gbu.restaurant.decompose.bottomnavholder.cart.add_address

import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressViewModel

interface AddAddressComponent {

    val viewModel: AddAddressViewModel

    fun addAddressInformation()

    fun popUp()
}