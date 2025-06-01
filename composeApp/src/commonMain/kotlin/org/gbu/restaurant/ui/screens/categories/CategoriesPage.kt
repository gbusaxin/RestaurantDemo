package org.gbu.restaurant.ui.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.local.entity.Category
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.noRippleClickable
import org.gbu.restaurant.ui.screens.categories.viewmodel.CategoriesEvent
import org.gbu.restaurant.ui.screens.categories.viewmodel.CategoriesState
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.categories

@Composable
fun CategoriesPage(
    state: CategoriesState,
    events: (CategoriesEvent) -> Unit,
    errors: Flow<UIComponent>,
    popUp: () -> Unit,
    navigateToSearch: (Long) -> Unit
) {

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(CategoriesEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.categories),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popUp
    ) {
        Column(Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)
            ) {
                items(items = state.categories, key = { it.id }) {
                    CategoryBox(it,  modifier = Modifier.weight(1f)) {
                        navigateToSearch(it.id)
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryBox(
    category: Category,
    modifier: Modifier = Modifier,
    onCategoryClicked: () -> Unit
) {
    Box(modifier = modifier.padding(8.dp), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.noRippleClickable {
                onCategoryClicked()
            }
        ) {
            Box(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.primary.copy(.2f), shape = CircleShape
                ).size(80.dp).padding(12.dp)
            ) {
                AsyncImage(
                    model = category.icon,
                    contentDescription = category.name
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = category.name,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}