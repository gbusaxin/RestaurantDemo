package org.gbu.restaurant.decompose.bottomnavholder.profile.my_coupons

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.my_coupons.viewmodel.MyCouponsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MyCouponsComponentImpl(
    componentContext: ComponentContext,
    private val onPopUp: () -> Unit
) : MyCouponsComponent, KoinComponent, ComponentContext by componentContext {
    override val viewModel: MyCouponsViewModel by inject<MyCouponsViewModel>()
    override fun popUp() {
        onPopUp()
    }
}