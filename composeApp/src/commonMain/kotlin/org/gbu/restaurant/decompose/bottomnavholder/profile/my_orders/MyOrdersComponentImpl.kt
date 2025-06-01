package org.gbu.restaurant.decompose.bottomnavholder.profile.my_orders

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.my_orders.viewmodel.MyOrdersViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MyOrdersComponentImpl(
    componentContext: ComponentContext,
    private val onPopUp: () -> Unit
) : MyOrdersComponent, KoinComponent, ComponentContext by componentContext {
    override val viewModel: MyOrdersViewModel by inject<MyOrdersViewModel>()

    override fun popUp() {
        onPopUp()
    }
}