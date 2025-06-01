package org.gbu.restaurant.decompose.bottomnavholder.cart.cart

import org.gbu.restaurant.ui.screens.cart.viewmodel.CartViewModel

interface CartComponent {

    val cartViewModel: CartViewModel

    fun onClickToDetail(menuItemId: Long)

    fun onClickToCheckout()

}