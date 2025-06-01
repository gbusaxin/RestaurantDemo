package org.gbu.restaurant.decompose.bottomnavholder.profile.my_orders

import org.gbu.restaurant.ui.screens.my_orders.viewmodel.MyOrdersViewModel

interface MyOrdersComponent {
    val viewModel: MyOrdersViewModel
    fun popUp()
}