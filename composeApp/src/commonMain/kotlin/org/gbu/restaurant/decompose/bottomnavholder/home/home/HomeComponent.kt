package org.gbu.restaurant.decompose.bottomnavholder.home.home

import org.gbu.restaurant.ui.screens.home.viewmodel.HomeViewModel

interface HomeComponent {
    val viewModel: HomeViewModel
    fun navigateToDetail(id: Long)
    fun navigateToNotifications()
    fun navigateToSettings()
    fun navigateToCategories()
    fun navigateToSearch(categoryId: Long?, sort: Int?)
}