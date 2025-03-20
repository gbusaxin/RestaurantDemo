package org.gbu.restaurant.ui.screens.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.entity.CartItem
import org.gbu.restaurant.ui.composables.DefaultButton
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.noRippleClickable
import org.gbu.restaurant.ui.screens.cart.viewmodel.CartEvent
import org.gbu.restaurant.ui.screens.cart.viewmodel.CartState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.cart_is_empty
import restaurantdemo.composeapp.generated.resources.proceed_to_checkout
import restaurantdemo.composeapp.generated.resources.total_cost

@Composable
fun CartPage(
    state: CartState,
    events: (CartEvent) -> Unit,
    errors: Flow<UIComponent>,
    navigateToDetail: (Long) -> Unit,
    navigateToCheckout: () -> Unit
) {

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(CartEvent.OnRetryNetwork) }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(top = 25.dp)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                items(state.cartItems) {
                    CartItemBox(
                        cartItem = it,
                        addMoreProducts = { events(CartEvent.AddMenuItem(it.menuItemId)) },
                        navigateToDetail = navigateToDetail
                    ) {
                        events(CartEvent.DeleteFromCart(it.menuItemId))
                    }
                }
            }

            if (state.cartItems.isNotEmpty()) {
                Box(
                    modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                ) {
                    ProceedButtonBox(
                        state.totalCost
                    ) {
                        navigateToCheckout()
                    }
                }
            }

            if (state.cartItems.isEmpty()) {
                Box(
                    modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        stringResource(Res.string.cart_is_empty),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Composable
fun ProceedButtonBox(totalCost: String, onCLick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomEnd = 6.dp, bottomStart = 6.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.total_cost),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = totalCost,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(Modifier.height(16.dp))

            DefaultButton(
                modifier = Modifier.fillMaxWidth().height(45.dp), // TODO check DEFAULT__BUTTON_SIZE
                text = stringResource(Res.string.proceed_to_checkout)
            ) {
                onCLick()
            }
        }
    }
}

@Composable
fun CartItemBox(
    cartItem: CartItem,
    navigateToDetail: (Long) -> Unit,
    addMoreProducts: () -> Unit,
    deleteFromCart: () -> Unit
) {
    var show by remember { mutableStateOf(true) }

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                deleteFromCart()
                show = false
                true
            } else false
        }
    )

    AnimatedVisibility(
        show, exit = fadeOut(spring())
    ) {
        SwipeToDismissBox(
            state = dismissState,
            modifier = Modifier,
            backgroundContent = {
                DismissBackground(dismissState)
            },
            content = {
                DismissCartContent(
                    cartItem = cartItem,
                    addMoreProducts = addMoreProducts,
                    navigateToDetail = navigateToDetail
                )
            }
        )
    }
}

@Composable
fun DismissCartContent(
    cartItem: CartItem,
    addMoreProducts: () -> Unit,
    navigateToDetail: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).height(130.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.fillMaxHeight().padding(vertical = 16.dp).weight(.3f)
                    .clip(MaterialTheme.shapes.small).noRippleClickable {
                        navigateToDetail(cartItem.menuItemId)
                    }
            ) {
                Image(
                    painter = painterResource(cartItem.image),
                    contentDescription = "product image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(Modifier.height(16.dp))

            Column(Modifier.weight(.4f)) {
                Text(
                    text = cartItem.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(4.dp)) // TODO add also item category in future
                Text(
                    text = cartItem.price,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium
                )
            }

            Row(
                modifier = Modifier.fillMaxHeight().weight(.3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally)
            ) {
                Card(
                    modifier = Modifier.size(35.dp),
                    shape = MaterialTheme.shapes.small,
                    onClick = {}, // TODO check if needs to be implemented
                    colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.surface)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("-")
                    }
                }
                Spacer(Modifier.height(4.dp))
                Text(cartItem.count.toString())
                Spacer(Modifier.height(4.dp))
                Card(
                    modifier = Modifier.size(35.dp),
                    shape = MaterialTheme.shapes.small,
                    onClick = { addMoreProducts() },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("+")
                    }
                }
            }
        }
        HorizontalDivider(Modifier.fillMaxWidth())
    }
}

@Composable
fun DismissBackground(dismissState: SwipeToDismissBoxState) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = .2f))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart)
            Icon(
                Icons.Default.Delete,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "delete icon"
            )
        Spacer(modifier = Modifier)
    }
}