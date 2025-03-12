package org.gbu.restaurant.decompose.menudetail

import org.gbu.restaurant.viewmodels.MenuDetailsViewModel

interface MenuDetailComponent {

    val viewModel: MenuDetailsViewModel
    val menuItemId: Long
    fun onBack()
}