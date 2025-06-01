package org.gbu.restaurant.decompose.bottomnavholder.profile.profile

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.profile.viewmodel.ProfileViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileComponentImpl(
    componentContext: ComponentContext,
    private val onNavigateToAddress: () -> Unit,
    private val onNavigateToEditProfile: () -> Unit,
    private val onNavigateToPaymentMethod: () -> Unit,
    private val onNavigateToMyOrders: () -> Unit,
    private val onNavigateToMyCoupons: () -> Unit,
    private val onNavigateToMyWallet: () -> Unit,
    private val onNavigateToSettings: () -> Unit
) : ProfileComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: ProfileViewModel by inject<ProfileViewModel>()

    override fun navigateToAddress() {
        onNavigateToAddress()
    }

    override fun navigateToEditProfile() {
        onNavigateToEditProfile()
    }

    override fun navigateToPaymentMethod() {
        onNavigateToPaymentMethod()
    }

    override fun navigateToMyOrders() {
        onNavigateToMyOrders()
    }

    override fun navigateToMyCoupons() {
        onNavigateToMyCoupons()
    }

    override fun navigateToMyWallet() {
        onNavigateToMyWallet()
    }

    override fun navigateToSettings() {
        onNavigateToSettings()
    }
}