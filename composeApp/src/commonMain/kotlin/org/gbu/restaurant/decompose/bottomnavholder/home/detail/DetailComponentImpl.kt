package org.gbu.restaurant.decompose.bottomnavholder.home.detail

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.detail.viewmodel.DetailViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailComponentImpl(
    componentContext: ComponentContext,
    private val onPopUp: () -> Unit,
    private val productId: Long
) : DetailComponent, ComponentContext by componentContext, KoinComponent {
    override val id: Long = productId
    override val viewModel: DetailViewModel by inject<DetailViewModel>()

    override fun popUp() {
        onPopUp()
    }
}