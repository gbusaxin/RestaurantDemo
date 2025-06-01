package org.gbu.restaurant.decompose.bottomnavholder.cart.checkout

import org.gbu.restaurant.ui.screens.checkout.viewmodel.CheckoutViewModel

interface CheckoutComponent {

    val viewModel: CheckoutViewModel

    fun navigateToAddress()

    fun popUp()

}