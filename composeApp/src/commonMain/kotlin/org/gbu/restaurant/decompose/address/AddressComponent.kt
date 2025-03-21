package org.gbu.restaurant.decompose.address

import org.gbu.restaurant.ui.screens.address.viewmodel.AddressViewModel

interface AddressComponent {

    val viewModel: AddressViewModel

    fun navigateToAddAddress()

    fun popUp()

}