package org.gbu.restaurant.decompose.bottomnavholder.home.search

import org.gbu.restaurant.ui.screens.search.viewmodel.SearchViewModel

interface SearchComponent {
    val viewModel: SearchViewModel
    fun navigateToDetailPage(id: Long)
    fun popUp()
}