package org.gbu.restaurant.ui.screens.wish_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.constants.PAGINATION_PAGE_SIZE
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.network.common.ProgressBarState
import org.gbu.restaurant.ui.composables.CategoryChipsBox
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.ProductBox
import org.gbu.restaurant.ui.screens.wish_list.viewmodel.WishListEvent
import org.gbu.restaurant.ui.screens.wish_list.viewmodel.WishListState
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.wish_list_is_empty

@Composable
fun WishListPage(
    state: WishListState,
    events: (WishListEvent) -> Unit,
    errors: Flow<UIComponent>,
    navigateToDetail: (Long) -> Unit
) {
    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(WishListEvent.OnRetryNetwork) }
    ) {
        Column(Modifier.fillMaxSize()) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.wishList.categories) {
                    CategoryChipsBox(category = it, isSelected = it == state.selectedCategory) {
                        events(WishListEvent.OnUpdateSelectedCategory(it))
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            if (state.wishList.products.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(Res.string.wish_list_is_empty),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.DarkGray
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                contentPadding = PaddingValues(8.dp),
                content = {
                    itemsIndexed(state.wishList.products) { index, product ->
                        if ((index + 1) >= (state.page * PAGINATION_PAGE_SIZE) &&
                            state.progressBarState == ProgressBarState.Idle &&
                            state.hasNextPage
                        ) {
                            events(WishListEvent.GetNextPage)
                        }

                        ProductBox(
                            product = product,
                            modifier = Modifier.fillMaxWidth(.5f),
                            onLikeClick = { events(WishListEvent.LikeProduct(product.id)) }
                        ) {
                            navigateToDetail(product.id)
                        }
                    }
                }
            )
        }
    }
}







