package org.gbu.restaurant.ui.screens.address

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.local.entity.Address
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.TextWithIcon
import org.gbu.restaurant.ui.screens.address.viewmodel.AddressEvent
import org.gbu.restaurant.ui.screens.address.viewmodel.AddressState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.address
import restaurantdemo.composeapp.generated.resources.delete_icon
import restaurantdemo.composeapp.generated.resources.earth_icon
import restaurantdemo.composeapp.generated.resources.location_icon
import restaurantdemo.composeapp.generated.resources.mail_icon
import restaurantdemo.composeapp.generated.resources.no_address

@Composable
fun AddressPage(
    state: AddressState,
    errors: Flow<UIComponent>,
    events: (AddressEvent) -> Unit,
    navigateToAddAddress: () -> Unit,
    popUp: () -> Unit
) {

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(AddressEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.address),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popUp,
        endIconToolbar = Icons.Filled.Add,
        onClickEndIconToolbar = navigateToAddAddress
    ) {
        Column(Modifier.fillMaxSize()) {
            if (state.addresses.isEmpty()) {
                Text(
                    stringResource(Res.string.no_address),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }

            LazyColumn {
                items(state.addresses, key = { it.id }) {
                    AddressBox(address = it)
                }
            }
        }
    }
}

@Composable
private fun AddressBox(address: Address) {
    Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.height(160.dp).fillMaxWidth().padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = address.address,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth(.9f)
                )
                Icon(
                    painter = painterResource(Res.drawable.delete_icon),
                    "delete address",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(Modifier.height(12.dp))

            TextWithIcon(text = address.country, icon = Res.drawable.earth_icon)
            Spacer(Modifier.height(4.dp))
            TextWithIcon(text = address.getFullAddress(), icon = Res.drawable.location_icon)
            Spacer(Modifier.height(4.dp))
            TextWithIcon(text = address.zipCode, icon = Res.drawable.mail_icon)
            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
        }
    }
}









