package org.gbu.restaurant.decompose.bottomnavholder.profile.my_coupons

import org.gbu.restaurant.ui.screens.my_coupons.viewmodel.MyCouponsViewModel

interface MyCouponsComponent {
    val viewModel: MyCouponsViewModel
    fun popUp()
}