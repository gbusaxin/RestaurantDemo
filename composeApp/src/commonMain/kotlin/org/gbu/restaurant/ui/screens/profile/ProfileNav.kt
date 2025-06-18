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
import org.gbu.restaurant.business.common.Context
import org.gbu.restaurant.decompose.bottomnavholder.profile.ProfileNavComponent
import org.gbu.restaurant.ui.screens.add_address.AddAddressInfoPage
import org.gbu.restaurant.ui.screens.add_address.AddAddressPage
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressViewModel
import org.gbu.restaurant.ui.screens.address.AddressPage
import org.gbu.restaurant.ui.screens.my_coupons.MyCouponsPage
import org.gbu.restaurant.ui.screens.my_orders.MyOrdersPage
import org.gbu.restaurant.ui.screens.payment_method.PaymentMethodPage
import org.gbu.restaurant.ui.screens.settings.SettingsPage

lateinit var addressViewModel: AddAddressViewModel

@Composable
fun ProfileNav(root: ProfileNavComponent, context: Context, logout: () -> Unit) {
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
                        navigateToPaymentMethod = { child.component.navigateToPaymentMethod() },
                        navigateToMyOrders = { child.component.navigateToMyOrders() },
                        navigateToMyCoupons = { child.component.navigateToMyCoupons() },
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

                is ProfileNavComponent.ProfileNavChild.AddAddress -> {
                    addressViewModel = child.component.viewModel
                    AddAddressPage(
                        context = context,
                        state = addressViewModel.state.value,
                        errors = addressViewModel.errors,
                        action = addressViewModel.action,
                        events = addressViewModel::onTriggerEvent,
                        navigationToAddInformation = { child.component.addAddressInformation() },
                        popUp = { child.component.popUp() }
                    )
                }

                is ProfileNavComponent.ProfileNavChild.AddAddressInfo -> {
                    AddAddressInfoPage(
                        state = addressViewModel.state.value,
                        errors = addressViewModel.errors,
                        action = addressViewModel.action,
                        events = addressViewModel::onTriggerEvent,
                        popUp = { child.component.popUp() }
                    )
                }

                is ProfileNavComponent.ProfileNavChild.Address -> {
                    val viewModel = child.component.viewModel
                    AddressPage(
                        state = viewModel.state.value,
                        errors = viewModel.errors,
                        events = viewModel::onTriggerEvent,
                        navigateToAddAddress = { child.component.navigateToAddAddress() },
                        popUp = { child.component.popUp() }
                    )
                }

                is ProfileNavComponent.ProfileNavChild.Settings -> {
                    val viewModel = child.component.viewModel
                    SettingsPage(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        action = viewModel.action,
                        popUp = { child.component.popUp() },
                        logout = logout
                    )
                }
            }
        }
    }
}