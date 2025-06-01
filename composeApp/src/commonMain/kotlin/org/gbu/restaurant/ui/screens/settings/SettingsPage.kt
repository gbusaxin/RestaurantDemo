package org.gbu.restaurant.ui.screens.settings

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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.noRippleClickable
import org.gbu.restaurant.ui.screens.settings.viewmodel.SettingsAction
import org.gbu.restaurant.ui.screens.settings.viewmodel.SettingsEvent
import org.gbu.restaurant.ui.screens.settings.viewmodel.SettingsState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.logout
import restaurantdemo.composeapp.generated.resources.logout_icon
import restaurantdemo.composeapp.generated.resources.settings

@Composable
fun SettingsPage(
    state: SettingsState,
    events: (SettingsEvent) -> Unit,
    errors: Flow<UIComponent>,
    action: Flow<SettingsAction>,
    popUp: () -> Unit,
    logout: () -> Unit
) {
    LaunchedEffect(key1 = action) {
        action.onEach { effect ->
            when (effect) {
                SettingsAction.Navigation.PopUp -> {
                    logout()
                }
            }
        }.collect {}
    }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(SettingsEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.settings),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popUp
    ) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Spacer(Modifier.height(32.dp))

            Row(Modifier.fillMaxWidth().padding(vertical = 16.dp).noRippleClickable {
                events(SettingsEvent.Logout)
            }, verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(Res.drawable.logout_icon),
                    contentDescription = "logout icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = stringResource(Res.string.logout),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(.9f)
                )
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForward,
                    "arrow forward",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
            }
            HorizontalDivider(color = Color.DarkGray)
        }
    }
}