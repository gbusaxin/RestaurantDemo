package org.gbu.restaurant.decompose.bottomnavholder.home.search

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.search.viewmodel.SearchViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchComponentImpl(
    context: ComponentContext,
    val onNavigateToDetailPage: (Long) -> Unit,
    val onPopUp: () -> Unit
): SearchComponent, ComponentContext by context, KoinComponent {
    override val viewModel: SearchViewModel by inject<SearchViewModel>()

    override fun navigateToDetailPage(id: Long) {
        onNavigateToDetailPage(id)
    }

    override fun popUp() {
        onPopUp()
    }
}