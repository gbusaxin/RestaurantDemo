package org.gbu.restaurant.decompose.menudetail

import org.gbu.restaurant.ui.screens.menu_detail.viewmodel.MenuDetailsViewModel

interface MenuDetailComponent {

    val viewModel: MenuDetailsViewModel
    val menuItemId: Long
    fun onBack()
}