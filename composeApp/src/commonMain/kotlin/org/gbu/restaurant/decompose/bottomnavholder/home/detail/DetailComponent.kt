package org.gbu.restaurant.decompose.bottomnavholder.home.detail

import org.gbu.restaurant.ui.screens.detail.viewmodel.DetailViewModel

interface DetailComponent {
    val id: Long
    val viewModel: DetailViewModel
    fun popUp()
}