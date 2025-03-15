package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.decompose.bottomnavholder.BottomNavComponentImpl
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
            contentColor = MaterialTheme.colorScheme.background, // TODO change to background
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