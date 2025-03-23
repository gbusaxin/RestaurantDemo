package org.gbu.restaurant.ui.screens.add_address

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.common.Context
import org.gbu.restaurant.business.common.MapComponent
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.IconButton
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressAction
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressEvent
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressState
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.add_new_address
import restaurantdemo.composeapp.generated.resources.confirm

@Composable
fun AddAddressPage(
    context: Context,
    state: AddAddressState,
    errors: Flow<UIComponent>,
    action: Flow<AddAddressAction>,
    events: (AddAddressEvent) -> Unit,
    navigationToAddInformation: () -> Unit,
    popUp: () -> Unit
) {

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        titleToolbar = stringResource(Res.string.add_new_address),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popUp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MapComponent(context = context, onLatitude = {
                println("AppDebug AddAddressScreen onLatitude:" + it)
                events(AddAddressEvent.OnUpdateLatitude(it))
            }, onLongitude = {
                println("AppDebug AddAddressScreen onLongitude:" + it)
                events(AddAddressEvent.OnUpdateLongitude(it))
            })
            IconButton(
                icon = Icons.Default.Done,
                text = stringResource(Res.string.confirm),
                modifier = Modifier.padding(16.dp).align(
                    Alignment.BottomStart
                )
            ){
                println("addAdd ${state}")
                navigationToAddInformation()
            }
        }
    }
}