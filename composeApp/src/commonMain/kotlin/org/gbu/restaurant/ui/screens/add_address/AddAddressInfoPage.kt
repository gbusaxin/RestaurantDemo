package org.gbu.restaurant.ui.screens.add_address

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.ui.composables.DefaultButton
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressAction
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressEvent
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressState
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.add_new_address
import restaurantdemo.composeapp.generated.resources.address_dot
import restaurantdemo.composeapp.generated.resources.city
import restaurantdemo.composeapp.generated.resources.country
import restaurantdemo.composeapp.generated.resources.state
import restaurantdemo.composeapp.generated.resources.submit
import restaurantdemo.composeapp.generated.resources.zip_code

@Composable
fun AddAddressInfoPage(
    state: AddAddressState,
    errors: Flow<UIComponent>,
    action: Flow<AddAddressAction>,
    events: (AddAddressEvent) -> Unit,
    popUp: () -> Unit
) {
    LaunchedEffect(key1 = action) {
        action.onEach { effect ->
            when (effect) {
                AddAddressAction.Navigation.PopUp -> {
                    popUp()
                }
            }
        }.collect {}
    }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        titleToolbar = stringResource(Res.string.add_new_address),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popUp
    ) {
        Column(Modifier.fillMaxWidth().padding(16.dp).verticalScroll(rememberScrollState())) {
            Text(
                text = stringResource(Res.string.country),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )
            TextField(
                modifier = Modifier.fillMaxWidth()
                    .border(1.dp, Color.DarkGray, MaterialTheme.shapes.small),
                value = state.country,
                onValueChange = { events(AddAddressEvent.OnUpdateCountry(it)) },
                colors = TextFieldDefaults.colors(),
                shape = MaterialTheme.shapes.small,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(Res.string.state),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )
            TextField(
                modifier = Modifier.fillMaxWidth()
                    .border(1.dp, Color.DarkGray, MaterialTheme.shapes.small),
                value = state.state,
                onValueChange = { events(AddAddressEvent.OnUpdateState(it)) },
                colors = TextFieldDefaults.colors(),
                shape = MaterialTheme.shapes.small,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(Res.string.city),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )
            TextField(
                modifier = Modifier.fillMaxWidth()
                    .border(1.dp, Color.DarkGray, MaterialTheme.shapes.small),
                value = state.state,
                onValueChange = { events(AddAddressEvent.OnUpdateCity(it)) },
                colors = TextFieldDefaults.colors(),
                shape = MaterialTheme.shapes.small,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(Res.string.address_dot),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )
            TextField(
                modifier = Modifier.fillMaxWidth()
                    .border(1.dp, Color.DarkGray, MaterialTheme.shapes.small),
                value = state.state,
                onValueChange = { events(AddAddressEvent.OnUpdateAddress(it)) },
                colors = TextFieldDefaults.colors(),
                shape = MaterialTheme.shapes.small,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(Res.string.zip_code),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )
            TextField(
                modifier = Modifier.fillMaxWidth()
                    .border(1.dp, Color.DarkGray, MaterialTheme.shapes.small),
                value = state.state,
                onValueChange = { events(AddAddressEvent.OnUpdateZipCode(it)) },
                colors = TextFieldDefaults.colors(),
                shape = MaterialTheme.shapes.small,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(Modifier.height(16.dp))

            DefaultButton(
                modifier = Modifier.fillMaxWidth().height(45.dp),
                text = stringResource(Res.string.submit)
            ) {
                events(AddAddressEvent.AddAddress)
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

















