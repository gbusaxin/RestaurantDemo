package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.business.constants.CurrencyConstants
import org.gbu.restaurant.business.core.UIComponentState
import org.gbu.restaurant.business.data.entity.ShippingType
import org.gbu.restaurant.ui.screens.checkout.viewmodel.CheckoutEvent
import org.gbu.restaurant.ui.screens.checkout.viewmodel.CheckoutState
import org.gbu.restaurant.ui.screens.checkout.viewmodel.shippingType_global
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.choose_delivery_type
import restaurantdemo.composeapp.generated.resources.delivery_icon

@Composable
fun SelectDeliveryDialog(
    state: CheckoutState,
    events: (CheckoutEvent) -> Unit
) {

    val shippingList = mutableStateOf(shippingType_global)

    CustomAlertDialog(
        onDismissRequest = {
            events(CheckoutEvent.OnUpdateSelectShippingDialogState(UIComponentState.Hide))
        },
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(Res.string.choose_delivery_type),
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(32.dp))
            LazyColumn {
                items(shippingList.value) {
                    DeliveryBox(it, it == state.selectedShipping) {
                        events(CheckoutEvent.OnUpdateSelectedShipping(it))
                    }
                }
            }
        }
    }
}

@Composable
fun DeliveryBox(
    deliveryType: ShippingType,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth().noRippleClickable { onClick() }) {
            Icon(
                painter = painterResource(Res.drawable.delivery_icon),
                "select delivery",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(16.dp))
            Column(Modifier.fillMaxWidth(.7f)) {
                Text(deliveryType.title, style = MaterialTheme.typography.titleMedium)
                Text(deliveryType.getEstimatedDate(), style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("${CurrencyConstants.CURRENCY} ${deliveryType.price}")
                Checkbox(isSelected, onCheckedChange = { onClick() })
            }
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = Color.DarkGray)
            Spacer(Modifier.height(8.dp))
        }
    }
}








