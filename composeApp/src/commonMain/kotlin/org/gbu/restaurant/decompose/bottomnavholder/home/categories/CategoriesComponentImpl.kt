package org.gbu.restaurant.decompose.bottomnavholder.home.categories

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.categories.viewmodel.CategoriesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CategoriesComponentImpl(
    context: ComponentContext,
    private val onPopUp: () -> Unit,
    private val onNavigateToSearch: (Long) -> Unit
) : CategoriesComponent, ComponentContext by context, KoinComponent {
    override val viewModel: CategoriesViewModel by inject<CategoriesViewModel>()

    override fun popUp() {
        onPopUp()
    }

    override fun navigateToSearch(id: Long) {
        onNavigateToSearch(id)
    }
}