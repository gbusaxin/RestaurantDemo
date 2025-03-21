package org.gbu.restaurant.decompose.checkout

import org.gbu.restaurant.ui.screens.checkout.viewmodel.CheckoutViewModel

interface CheckoutComponent {

    val viewModel: CheckoutViewModel

    fun navigateToAddress()

    fun popUp()

}