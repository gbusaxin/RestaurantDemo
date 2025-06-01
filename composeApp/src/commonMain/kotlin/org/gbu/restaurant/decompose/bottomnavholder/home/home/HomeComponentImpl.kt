package org.gbu.restaurant.decompose.bottomnavholder.home.home

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.home.viewmodel.HomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeComponentImpl(
    context: ComponentContext,
    val onNavigationToDetail: (id: Long) -> Unit,
    val onNavigateToNotifications: () -> Unit,
    val onNavigateToSettings: () -> Unit,
    val onNavigateToCategories: () -> Unit,
    val onNavigateToSearch: (categoryId: Long?, sort: Int?) -> Unit
) : HomeComponent, ComponentContext by context, KoinComponent {
    override val viewModel: HomeViewModel by inject<HomeViewModel>()
    override fun navigateToDetail(id: Long) {
        onNavigationToDetail(id)
    }

    override fun navigateToNotifications() {
        onNavigateToNotifications()
    }

    override fun navigateToSettings() {
        onNavigateToSettings()
    }

    override fun navigateToCategories() {
        onNavigateToCategories()
    }

    override fun navigateToSearch(categoryId: Long?, sort: Int?) {
        onNavigateToSearch(categoryId, sort)
    }
}