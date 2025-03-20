package org.gbu.restaurant.decompose.cart

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.cart.viewmodel.CartViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CartComponentImpl(
    private val componentContext: ComponentContext,
    private val clickToDetail: (Long) -> Unit,
    private val clickToCheckout: () -> Unit
) : CartComponent, ComponentContext by componentContext, KoinComponent {
    override val cartViewModel: CartViewModel by inject<CartViewModel>()

    override fun onClickToDetail(menuItemId: Long) {
        clickToDetail(menuItemId)
    }

    override fun onClickToCheckout() {
        clickToCheckout()
    }

}