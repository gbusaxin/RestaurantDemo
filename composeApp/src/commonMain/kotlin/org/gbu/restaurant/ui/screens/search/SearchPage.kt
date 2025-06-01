package org.gbu.restaurant.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.core.UIComponentState
import org.gbu.restaurant.business.data.local.entity.Product
import org.gbu.restaurant.ui.composables.CircleButton
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.FilterDialog
import org.gbu.restaurant.ui.composables.SortDialog
import org.gbu.restaurant.ui.composables.noRippleClickable
import org.gbu.restaurant.ui.composables.rememberCustomImagePainter
import org.gbu.restaurant.ui.screens.search.viewmodel.SearchEvent
import org.gbu.restaurant.ui.screens.search.viewmodel.SearchState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.close_icon
import restaurantdemo.composeapp.generated.resources.filter
import restaurantdemo.composeapp.generated.resources.filter_icon
import restaurantdemo.composeapp.generated.resources.search_icon
import restaurantdemo.composeapp.generated.resources.search_with_dots
import restaurantdemo.composeapp.generated.resources.sort
import restaurantdemo.composeapp.generated.resources.sort_icon

@Composable
fun SearchPage(
    state: SearchState,
    events: (SearchEvent) -> Unit,
    errors: Flow<UIComponent>,
    navigateToDetailPage: (Long) -> Unit,
    popUp: () -> Unit
) {
    if (state.filterDialogState == UIComponentState.Show) {
        FilterDialog(state = state, events = events)
    }

    if (state.sortDialogState == UIComponentState.Show) {
        SortDialog(state = state, events = events)
    }

    DefaultScreenUI(
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        errors = errors,
        onTryAgain = { events(SearchEvent.OnRetryNetwork) }
    ) {
        Column(Modifier.fillMaxSize()) {
            Row(Modifier.fillMaxWidth().padding(16.dp)) {
                CircleButton(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    onClick = { popUp() }
                )
                Spacer(Modifier.height(8.dp))
                SearchBox(
                    value = state.searchText,
                    onValueChange = { events(SearchEvent.OnUpdateSearchText(it)) },
                    onSearchExecute = { events(SearchEvent.Search()) }
                )
            }
            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                TextButton(
                    onClick = {
                        events(
                            SearchEvent.OnUpdateSortDialogState(UIComponentState.Show)
                        )
                    },
                    modifier = Modifier.weight(5f),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.sort_icon),
                            contentDescription = "sort icon",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = stringResource(Res.string.sort),
                        )
                    }
                }
                TextButton(
                    onClick = {
                        events(SearchEvent.OnUpdateFilterDialogState(UIComponentState.Show))
                    },
                    modifier = Modifier.weight(5f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.filter_icon),
                            contentDescription = "filter icon",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = stringResource(Res.string.filter),
                        )
                    }
                }
            }
            Column(Modifier.background(MaterialTheme.colorScheme.surface)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(state.search.products, key = { it.id }) {
                        ProductSearchBox(
                            product = it,
                            isLastItem = state.search.products.last() == it,
                            navigateToDetailPage = { navigateToDetailPage(it.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductSearchBox(
    product: Product,
    isLastItem: Boolean,
    navigateToDetailPage: () -> Unit
) {
    Box(Modifier.fillMaxWidth().noRippleClickable { navigateToDetailPage() }) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(90.dp).clip(RoundedCornerShape(16.dp)),
                    painter = rememberCustomImagePainter(product.image),
                    contentDescription = "product image",
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(16.dp))
                Column {
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = product.category.name,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = product.getPrice(),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            if (!isLastItem) HorizontalDivider(
                Modifier.fillMaxWidth().padding(vertical = 16.dp)
            )
        }
    }
}

@Composable
private fun SearchBox(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchExecute: () -> Unit
) {
    val keyBoardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
            .fillMaxWidth().height(55.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(Res.drawable.search_icon),
                contentDescription = "search icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp).size(30.dp).noRippleClickable {
                    onSearchExecute()
                    keyBoardController?.hide()
                }
            )
            TextField(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(.8f),
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = stringResource(Res.string.search_with_dots),
                        color = Color.White.copy(alpha = 0.7f)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    onSearchExecute()
                    keyBoardController?.hide()
                }),
                singleLine = true
            )

            Icon(
                painter = painterResource(Res.drawable.close_icon),
                contentDescription = "close icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp).size(30.dp).noRippleClickable {
                    onValueChange("")
                    keyBoardController?.hide()
                }
            )
        }
    }
}