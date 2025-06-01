package org.gbu.restaurant.ui.screens.payment_method

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.noRippleClickable
import org.gbu.restaurant.ui.screens.payment_method.viewmodel.PaymentMethodEvent
import org.gbu.restaurant.ui.screens.payment_method.viewmodel.PaymentMethodState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.apple_pay
import restaurantdemo.composeapp.generated.resources.google_pay
import restaurantdemo.composeapp.generated.resources.more_payment_options
import restaurantdemo.composeapp.generated.resources.payment_methods
import restaurantdemo.composeapp.generated.resources.paypal
import restaurantdemo.composeapp.generated.resources.paypal_icon
import restaurantdemo.composeapp.generated.resources.wallet
import restaurantdemo.composeapp.generated.resources.wallet_icon

@Composable
fun PaymentMethodPage(
    errors: Flow<UIComponent>,
    state: PaymentMethodState,
    events: (PaymentMethodEvent) -> Unit,
    popUp: () -> Unit
) {
    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(PaymentMethodEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.payment_methods),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popUp
    ) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Spacer(Modifier.height(32.dp))

            Text(
                text = stringResource(Res.string.wallet),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(8.dp))
            ChipsCardBox(
                text = stringResource(Res.string.wallet),
                image = Res.drawable.wallet_icon,
                isSelected = state.selectedPaymentMethod == 0,
                onSelectedExecute = { events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(0)) }
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(Res.string.more_payment_options),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(8.dp))

            Card(
                colors = CardDefaults.cardColors(),
                modifier = Modifier,
                border = BorderStroke(1.dp, Color.DarkGray),
                shape = MaterialTheme.shapes.small
            ) {
                Column(Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.padding(12.dp).fillMaxWidth().noRippleClickable {
                            events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(2))
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.paypal_icon),
                            contentDescription = "paypal payment",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = stringResource(Res.string.paypal),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Switch(
                        checked = state.selectedPaymentMethod == 2, onCheckedChange = {
                            events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(2))
                        }
                    )
                }
                HorizontalDivider(color = Color.DarkGray)
                Row(
                    modifier = Modifier.padding(12.dp).fillMaxWidth().noRippleClickable {
                        events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(3))
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.apple_pay),
                            contentDescription = "apple pay",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = stringResource(Res.string.apple_pay),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Switch(checked = state.selectedPaymentMethod == 3, onCheckedChange = {
                        events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(3))
                    })
                }

                HorizontalDivider(color = Color.DarkGray)
                Row(
                    modifier = Modifier.padding(12.dp).fillMaxWidth().noRippleClickable {
                        events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(4))
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.google_pay),
                            contentDescription = "google pay",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = stringResource(Res.string.google_pay),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Switch(checked = state.selectedPaymentMethod == 4, onCheckedChange = {
                        events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(4))
                    })
                }
            }
        }
    }
}

@Composable
private fun ChipsCardBox(
    text: String,
    image: DrawableResource,
    isSelected: Boolean,
    onSelectedExecute: () -> Unit
) {
    Card(
        onClick = onSelectedExecute,
        modifier = Modifier,
        border = BorderStroke(1.dp, Color.DarkGray),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(image),
                    "",
                    modifier = Modifier.size(24.dp)
                )
                Text(text, style = MaterialTheme.typography.bodyLarge)
            }
            Switch(
                checked = isSelected, onCheckedChange = { onSelectedExecute() }
            )
        }
    }
}