package org.gbu.restaurant.decompose.bottomnavholder.wish_list.wish_list

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.wish_list.viewmodel.WishListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WishListComponentImpl(
    componentContext: ComponentContext,
    private val onNavigateToDetail: (Long) -> Unit
): WishListComponent, KoinComponent {
    override val viewModel: WishListViewModel by inject<WishListViewModel>()

    override fun navigateToDetail(id: Long) {
        onNavigateToDetail(id)
    }
}