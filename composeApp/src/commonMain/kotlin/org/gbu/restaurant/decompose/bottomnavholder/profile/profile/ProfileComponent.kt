package org.gbu.restaurant.decompose.bottomnavholder.profile.profile

import org.gbu.restaurant.ui.screens.profile.viewmodel.ProfileViewModel

interface ProfileComponent {
    val viewModel: ProfileViewModel
    fun navigateToAddress()
    fun navigateToEditProfile()
    fun navigateToPaymentMethod()
    fun navigateToMyOrders()
    fun navigateToMyCoupons()
    fun navigateToMyWallet()
    fun navigateToSettings()
}