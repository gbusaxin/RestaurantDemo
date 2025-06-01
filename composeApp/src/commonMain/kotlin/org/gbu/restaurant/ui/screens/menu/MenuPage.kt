package org.gbu.restaurant.ui.screens.menu

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.gbu.restaurant.business.data.local.entity.MenuItem
import org.gbu.restaurant.viewmodels.MenuViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MenuPage(
    menuViewModel: MenuViewModel,
    onClick: (menuItem: MenuItem) -> Unit,
    sharedTransitionScope: SharedTransitionScope
) {
    val items by menuViewModel.items.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        val listState = rememberLazyGridState()
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary),
            state = listState,
            columns = GridCells.Fixed(1)
        ) {
            item {
                Spacer(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars))
            }
            items(items.size) { item ->
                val menuItem = items[item]
                MenuListItemWrapper(
                    scrollDirection = listState.isScrollingUp(),
                    child = {
                        MenuListItem(
                            menuItem = menuItem,
                            onClick = onClick,
                            sharedTransitionScope = sharedTransitionScope
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun LazyGridState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}