package org.gbu.restaurant.decompose.bottomnavholder.wish_list.wish_list

import org.gbu.restaurant.ui.screens.wish_list.viewmodel.WishListViewModel

interface WishListComponent {
    val viewModel: WishListViewModel
    fun navigateToDetail(id: Long)
}