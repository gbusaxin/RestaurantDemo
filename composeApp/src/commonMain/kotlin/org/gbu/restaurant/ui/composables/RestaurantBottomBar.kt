package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.decompose.bottomnavholder.BottomNavComponentImpl
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

// TODO delete it
@Composable
fun RestaurantBottomBar(
    pages: List<BottomNavComponentImpl.BottomNavConfig>,
    current: BottomNavComponentImpl.BottomNavConfig,
    onNavigate: (index: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        pages.forEach { config ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            if (config != current) {
                                onNavigate(pages.indexOf(config))
                            }
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides if (current == config) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(config.icon),
                        contentDescription = stringResource(config.title),
                        tint = LocalContentColor.current
                    )
                    Text(
                        text = stringResource(config.title),
                        style = MaterialTheme.typography.titleSmall
                            .copy(
                                fontWeight = FontWeight.Medium,
                                color = LocalContentColor.current
                            ),
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavUI(
    pages: List<BottomNavComponentImpl.BottomNavConfig>,
    current: BottomNavComponentImpl.BottomNavConfig,
    onNavigate: (index: Int) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        )
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground, // TODO change to background
            tonalElevation = 6.dp
        ) {
            pages.forEach {
                NavigationBarItem(
                    label = { Text(stringResource(it.title)) },
                    colors = NavigationBarItemDefaults.colors(),
                    selected = it == current,
                    icon = {
                        Icon(
                            painter = painterResource(it.icon),
                            contentDescription = stringResource(it.title),
                            tint = Color.Unspecified
                        )
                    },
                    onClick = {
                        if (current != it) {
                            onNavigate(pages.indexOf(it))
                        }
                    }
                )
            }
        }
    }
}