package org.gbu.restaurant.ui.screens.checkout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import org.gbu.restaurant.business.constants.CurrencyConstants
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.core.UIComponentState
import org.gbu.restaurant.business.data.local.entity.Address
import org.gbu.restaurant.ui.composables.DefaultButton
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.SelectDeliveryDialog
import org.gbu.restaurant.ui.composables.noRippleClickable
import org.gbu.restaurant.ui.screens.checkout.viewmodel.CheckoutAction
import org.gbu.restaurant.ui.screens.checkout.viewmodel.CheckoutEvent
import org.gbu.restaurant.ui.screens.checkout.viewmodel.CheckoutState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.back_to_checkout
import restaurantdemo.composeapp.generated.resources.change
import restaurantdemo.composeapp.generated.resources.choose_delivery_type
import restaurantdemo.composeapp.generated.resources.delivery_address
import restaurantdemo.composeapp.generated.resources.delivery_cost
import restaurantdemo.composeapp.generated.resources.delivery_icon
import restaurantdemo.composeapp.generated.resources.home
import restaurantdemo.composeapp.generated.resources.location_icon
import restaurantdemo.composeapp.generated.resources.submit
import restaurantdemo.composeapp.generated.resources.total_cost

@Composable
fun CheckoutPage(
    state: CheckoutState,
    errors: Flow<UIComponent>,
    action: Flow<CheckoutAction>,
    events: (CheckoutEvent) -> Unit,
    navigateToAddress: () -> Unit,
    popUp: () -> Unit
) {

    LaunchedEffect(key1 = action) {
        action.onEach { effect ->
            when (effect) {
                CheckoutAction.Navigation.PopUp -> {
                    popUp()
                }
            }
        }.collect {}
    }

    if (state.selectShippingDialogState == UIComponentState.Show) {
        SelectDeliveryDialog(state = state, events = events)
    }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(CheckoutEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.back_to_checkout),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popUp
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize().padding(16.dp).align(Alignment.TopCenter)) {
                Spacer(Modifier.height(32.dp))
                Text(
                    text = stringResource(Res.string.delivery_address),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(12.dp))
                DeliveryBox(
                    title = stringResource(Res.string.home),
                    image = Res.drawable.location_icon,
                    detail = state.selectedAddress.getDeliveryAddress()
                ) {
                    navigateToAddress()
                }
                Spacer(Modifier.height(16.dp))
                HorizontalDivider(color = Color.DarkGray)
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(Res.string.choose_delivery_type),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(12.dp))
                DeliveryBox(
                    title = state.selectedShipping.title,
                    image = Res.drawable.delivery_icon,
                    detail = state.selectedShipping.getEstimatedDate()
                ) {
                    events(CheckoutEvent.OnUpdateSelectShippingDialogState(UIComponentState.Show))
                }
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()) {
                CheckoutButtonBox(
                    "${CurrencyConstants.CURRENCY} ${state.totalCost}",
                    "${CurrencyConstants.CURRENCY} ${state.selectedShipping.price}",
                    selectedAddress = state.selectedAddress
                ) {
                    events(CheckoutEvent.BuyProduct)
                }
            }
        }
    }
}

@Composable
fun CheckoutButtonBox(
    totalCost: String,
    shippingCost: String,
    selectedAddress: Address,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(),
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.delivery_cost),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = shippingCost, style = MaterialTheme.typography.titleLarge)
            }

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.total_cost),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = totalCost, style = MaterialTheme.typography.titleLarge)
            }

            Spacer(Modifier.height(8.dp))

            DefaultButton(
                modifier = Modifier.fillMaxWidth().height(45.dp),
                text = stringResource(Res.string.submit),
                enabled = selectedAddress != Address()
            ) {
                onClick()
            }
        }
    }
}

@Composable
fun DeliveryBox(
    title: String,
    image: DrawableResource,
    detail: String,
    onClick: () -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(image),
            "delivery",
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(8.dp))
        Column(Modifier.fillMaxWidth(.7f)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(detail, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(Modifier.height(8.dp))
        Box(Modifier.wrapContentHeight(), contentAlignment = Alignment.CenterEnd) {
            Box(
                modifier = Modifier.border(
                    1.dp,
                    color = Color.DarkGray,
                    shape = MaterialTheme.shapes.medium
                ).noRippleClickable { onClick() }
            ) {
                Text(
                    text = stringResource(Res.string.change),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}









