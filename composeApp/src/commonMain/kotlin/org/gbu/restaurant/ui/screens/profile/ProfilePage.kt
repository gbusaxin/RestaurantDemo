package org.gbu.restaurant.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.noRippleClickable
import org.gbu.restaurant.ui.screens.profile.viewmodel.ProfileEvent
import org.gbu.restaurant.ui.screens.profile.viewmodel.ProfileState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.address
import restaurantdemo.composeapp.generated.resources.compose_multiplatform
import restaurantdemo.composeapp.generated.resources.coupon_icon
import restaurantdemo.composeapp.generated.resources.edit_profile
import restaurantdemo.composeapp.generated.resources.help_and_support
import restaurantdemo.composeapp.generated.resources.help_and_support_icon
import restaurantdemo.composeapp.generated.resources.location_icon
import restaurantdemo.composeapp.generated.resources.my_coupons
import restaurantdemo.composeapp.generated.resources.my_orders
import restaurantdemo.composeapp.generated.resources.order_icon
import restaurantdemo.composeapp.generated.resources.payment_icon
import restaurantdemo.composeapp.generated.resources.payment_methods
import restaurantdemo.composeapp.generated.resources.profile
import restaurantdemo.composeapp.generated.resources.settings
import restaurantdemo.composeapp.generated.resources.settings_icon
import restaurantdemo.composeapp.generated.resources.user_profile_icon

@Composable
fun ProfilePage(
    state: ProfileState,
    events: (ProfileEvent) -> Unit,
    errors: Flow<UIComponent>,
    navigateToAddress: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToPaymentMethod: () -> Unit,
    navigateToMyOrders: () -> Unit,
    navigateToMyCoupons: () -> Unit,
    navigateToMyWallet: () -> Unit,
    navigateToSettings: () -> Unit,
) {

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(ProfileEvent.OnRetryNetwork) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(Res.string.profile),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = state.profile.name,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(32.dp))

            Column(Modifier.fillMaxWidth()) {
                ProfileItemBox(
                    title = stringResource(Res.string.edit_profile),
                    image = Res.drawable.user_profile_icon,
                ) {
                    navigateToEditProfile()
                }
                ProfileItemBox(
                    title = stringResource(Res.string.address),
                    image = Res.drawable.location_icon,
                ) {
                    navigateToAddress()
                }
                ProfileItemBox(
                    title = stringResource(Res.string.payment_methods),
                    image = Res.drawable.payment_icon,
                ) {
                    navigateToPaymentMethod()
                }
                ProfileItemBox(
                    title = stringResource(Res.string.my_orders),
                    image = Res.drawable.order_icon,
                ) {
                    navigateToMyOrders()
                }
                ProfileItemBox(
                    title = stringResource(Res.string.my_coupons),
                    image = Res.drawable.coupon_icon,
                ) {
                    navigateToMyCoupons()
                }
                ProfileItemBox(
                    title = stringResource(Res.string.settings),
                    image = Res.drawable.settings_icon,
                ) {
                    navigateToSettings()
                }
                ProfileItemBox(
                    title = stringResource(Res.string.help_and_support),
                    image = Res.drawable.help_and_support_icon,
                    isLastItem = true
                ) {
                    TODO()
                }
            }
        }
    }
}

@Composable
private fun ProfileItemBox(
    title: String,
    image: DrawableResource,
    isLastItem: Boolean = false,
    onClick: () -> Unit
) {
    Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp).noRippleClickable {
        onClick()
    }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(image),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(35.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    null,
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = .7f),
                    modifier = Modifier.size(30.dp)
                )
            }
            if (!isLastItem) {
                Spacer(Modifier.height(12.dp))
                HorizontalDivider()
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}


















