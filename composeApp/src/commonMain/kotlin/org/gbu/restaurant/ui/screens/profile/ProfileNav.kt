package org.gbu.restaurant.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import org.gbu.restaurant.decompose.bottomnavholder.profile.ProfileNavComponent
import org.gbu.restaurant.ui.screens.my_coupons.MyCouponsPage
import org.gbu.restaurant.ui.screens.my_orders.MyOrdersPage
import org.gbu.restaurant.ui.screens.payment_method.PaymentMethodPage

@Composable
fun ProfileNav(root: ProfileNavComponent) {
    val stack by root.pages.subscribeAsState()

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Children(
            stack = stack,
            modifier = Modifier.weight(1f)
        ) { childCreated ->
            when (val child = childCreated.instance) {
                is ProfileNavComponent.ProfileNavChild.Profile -> {
                    val viewModel = child.component.viewModel
                    ProfilePage(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        navigateToAddress = { child.component.navigateToAddress() },
                        navigateToEditProfile = { child.component.navigateToEditProfile() },
                        navigateToPaymentMethod = { child.component.navigateToPaymentMethod() },
                        navigateToMyOrders = { child.component.navigateToMyOrders() },
                        navigateToMyCoupons = { child.component.navigateToMyCoupons() },
                        navigateToMyWallet = { child.component.navigateToMyWallet() },
                        navigateToSettings = { child.component.navigateToSettings() }
                    )
                }

                is ProfileNavComponent.ProfileNavChild.MyCoupons -> {
                    val viewModel = child.component.viewModel
                    MyCouponsPage(
                        state = viewModel.state.value,
                        errors = viewModel.errors,
                        events = viewModel::onTriggerEvent,
                        popUp = { child.component.popUp() }
                    )
                }

                is ProfileNavComponent.ProfileNavChild.Payment -> {
                    val viewModel = child.component.viewModel
                    PaymentMethodPage(
                        errors = viewModel.errors,
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        popUp = { child.component.popUp() }
                    )
                }

                is ProfileNavComponent.ProfileNavChild.MyOrders -> {
                    val viewModel = child.component.viewModel
                    MyOrdersPage(
                        state = viewModel.state.value,
                        errors = viewModel.errors,
                        events = viewModel::onTriggerEvent,
                        popUp = { child.component.popUp() }
                    )
                }
            }
        }
    }
}