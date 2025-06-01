package org.gbu.restaurant.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.local.entity.Notification
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.screens.notifications.viewmodel.NotificationsEvent
import org.gbu.restaurant.ui.screens.notifications.viewmodel.NotificationsState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.mark_all_as_read
import restaurantdemo.composeapp.generated.resources.nothing_yet
import restaurantdemo.composeapp.generated.resources.notifications
import restaurantdemo.composeapp.generated.resources.notifications_icon

@Composable
fun NotificationsPage(
    state: NotificationsState,
    events: (NotificationsEvent) -> Unit,
    errors: Flow<UIComponent>,
    popUp: () -> Unit
) {

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(NotificationsEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.notifications),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popUp
    ) {
        if (state.notifications.isEmpty()) {
            Text(
                text = stringResource(Res.string.nothing_yet),
                style = MaterialTheme.typography.titleLarge,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxSize().padding(top = 65.dp),
                textAlign = TextAlign.Center
            )
        } else {
            Column(Modifier.fillMaxSize()) {
                Text(
                    text = stringResource(Res.string.mark_all_as_read),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn(Modifier.fillMaxSize()) {
                    items(state.notifications) {
                        NotificationsBox(it)
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationsBox(notification: Notification) {
    Column(
        Modifier.fillMaxWidth()
            .background(
                if (notification.isRead) MaterialTheme.colorScheme.background
                else Color.LightGray
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                    shape = CircleShape
                ).size(75.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(Res.drawable.notifications_icon),
                    "notifications icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(35.dp)
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(.9f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = notification.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = notification.createAt,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Spacer(Modifier.height(8.dp))
        HorizontalDivider(color = Color.DarkGray)
    }
}