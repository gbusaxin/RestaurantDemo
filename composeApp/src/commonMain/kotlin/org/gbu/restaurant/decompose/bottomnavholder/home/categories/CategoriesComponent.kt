package org.gbu.restaurant.decompose.bottomnavholder.home.categories

import org.gbu.restaurant.ui.screens.categories.viewmodel.CategoriesViewModel

interface CategoriesComponent {
    val viewModel: CategoriesViewModel
    fun popUp()
    fun navigateToSearch(id: Long)
}