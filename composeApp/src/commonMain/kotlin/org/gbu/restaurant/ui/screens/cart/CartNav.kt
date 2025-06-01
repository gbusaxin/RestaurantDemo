package org.gbu.restaurant.ui.screens.cart

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
import org.gbu.restaurant.decompose.bottomnavholder.cart.CartNavComponent
import org.gbu.restaurant.ui.screens.add_address.AddAddressInfoPage
import org.gbu.restaurant.ui.screens.add_address.AddAddressPage
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressViewModel
import org.gbu.restaurant.ui.screens.address.AddressPage
import org.gbu.restaurant.ui.screens.checkout.CheckoutPage

lateinit var addressViewModel: AddAddressViewModel

@Composable
fun CartNav(root: CartNavComponent, context: Context) {
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
                is CartNavComponent.CartNavChild.AddAddress -> {
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

                is CartNavComponent.CartNavChild.AddAddressInfo -> {
                    AddAddressInfoPage(
                        state = addressViewModel.state.value,
                        errors = addressViewModel.errors,
                        action = addressViewModel.action,
                        events = addressViewModel::onTriggerEvent,
                        popUp = { child.component.popUp() }
                    )
                }

                is CartNavComponent.CartNavChild.Address -> {
                    val viewModel = child.component.viewModel
                    AddressPage(
                        state = viewModel.state.value,
                        errors = viewModel.errors,
                        events = viewModel::onTriggerEvent,
                        navigateToAddAddress = { child.component.navigateToAddAddress() },
                        popUp = { child.component.popUp() }
                    )
                }

                is CartNavComponent.CartNavChild.Cart -> {
                    val viewModel = child.component.cartViewModel
                    CartPage(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        navigateToDetail = { child.component.onClickToDetail(it) },
                        navigateToCheckout = { child.component.onClickToCheckout() }
                    )
                }

                is CartNavComponent.CartNavChild.Checkout -> {
                    val viewModel = child.component.viewModel
                    CheckoutPage(
                        state = viewModel.state.value,
                        errors = viewModel.errors,
                        action = viewModel.action,
                        events = viewModel::onTriggerEvent,
                        navigateToAddress = { child.component.navigateToAddress() },
                        popUp = { child.component.popUp() }
                    )
                }
            }
        }
    }
}