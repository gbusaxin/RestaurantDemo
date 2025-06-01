package org.gbu.restaurant.ui.screens.my_orders

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.gbu.restaurant.business.constants.SHIPPING_ACTIVE
import org.gbu.restaurant.business.constants.SHIPPING_CANCELED
import org.gbu.restaurant.business.constants.SHIPPING_FAILED
import org.gbu.restaurant.business.constants.SHIPPING_SUCCESS
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.local.entity.Order
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.noRippleClickable
import org.gbu.restaurant.ui.screens.my_orders.viewmodel.MyOrdersEvent
import org.gbu.restaurant.ui.screens.my_orders.viewmodel.MyOrdersState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.active
import restaurantdemo.composeapp.generated.resources.address
import restaurantdemo.composeapp.generated.resources.amount
import restaurantdemo.composeapp.generated.resources.arrow_down_icon
import restaurantdemo.composeapp.generated.resources.canceled
import restaurantdemo.composeapp.generated.resources.delivery_cost
import restaurantdemo.composeapp.generated.resources.delivery_type
import restaurantdemo.composeapp.generated.resources.download_icon
import restaurantdemo.composeapp.generated.resources.failed
import restaurantdemo.composeapp.generated.resources.image_placeholder_icon
import restaurantdemo.composeapp.generated.resources.my_orders
import restaurantdemo.composeapp.generated.resources.nothing_yet
import restaurantdemo.composeapp.generated.resources.promo_code
import restaurantdemo.composeapp.generated.resources.success

@Composable
fun MyOrdersPage(
    state: MyOrdersState,
    errors: Flow<UIComponent>,
    events: (MyOrdersEvent) -> Unit,
    popUp: () -> Unit
) {
    val scope = rememberCoroutineScope()

    val tabList by remember {
        mutableStateOf(
            listOf(
                Res.string.active,
                Res.string.success,
                Res.string.failed,
                Res.string.canceled
            )
        )
    }

    val pagerState = rememberPagerState { tabList.size }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(MyOrdersEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.my_orders),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popUp
    ) {
        Column(Modifier.fillMaxSize()) {
            TabRow(
                modifier = Modifier.height(50.dp).fillMaxWidth(),
                selectedTabIndex = pagerState.currentPage,
                contentColor = Color.Transparent,
                containerColor = Color.Transparent,
                divider = {},
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .height(4.dp).padding(horizontal = 28.dp).background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.medium
                            )
                    )
                }
            ) {
                tabList.forEachIndexed { index, _ ->
                    Tab(
                        unselectedContentColor = Color.Transparent,
                        selectedContentColor = Color.Transparent,
                        text = {
                            Text(
                                text = stringResource(tabList[index]),
                                style = MaterialTheme.typography.labelLarge,
                                color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onBackground
                            )
                        },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch { pagerState.animateScrollToPage(index) }
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Top
            ) { index ->
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier.fillMaxSize()
                ) {
                    when (index) {
                        SHIPPING_ACTIVE -> {
                            MyOrdersList(list = state.orders.filter { it.status == SHIPPING_ACTIVE })
                        }

                        SHIPPING_SUCCESS -> {
                            MyOrdersList(list = state.orders.filter { it.status == SHIPPING_SUCCESS })
                        }

                        SHIPPING_FAILED -> {
                            MyOrdersList(list = state.orders.filter { it.status == SHIPPING_FAILED })
                        }

                        SHIPPING_CANCELED -> {
                            MyOrdersList(list = state.orders.filter { it.status == SHIPPING_CANCELED })
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MyOrdersList(list: List<Order>) {
    if (list.isEmpty()) {
        Text(
            stringResource(Res.string.nothing_yet),
            style = MaterialTheme.typography.titleLarge,
            color = Color.DarkGray,
            modifier = Modifier.fillMaxSize().padding(top = 64.dp),
            textAlign = TextAlign.Center
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(list, key = { it.createdAt }) {
            OrderBox(it)
        }
    }
}

@Composable
private fun OrderBox(order: Order) {
    var isExpanded by remember { mutableStateOf(false) }

    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(350, easing = LinearEasing)
    )

    Box(Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                .border(1.dp, Color.DarkGray, MaterialTheme.shapes.medium)
                .animateContentSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = order.createdAt, // TODO(there must be converter)
                    style = MaterialTheme.typography.bodyLarge
                )
                Icon(
                    painter = painterResource(Res.drawable.arrow_down_icon),
                    contentDescription = "open",
                    modifier = Modifier.size(35.dp).padding(4.dp).rotate(rotationState)
                        .noRippleClickable { isExpanded = !isExpanded }
                )
            }
            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(Res.string.promo_code),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = order.code,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(Modifier.height(8.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(order.products) {
                    AsyncImage(
                        it.image,
                        null,
                        modifier = Modifier.size(55.dp).padding(horizontal = 4.dp),
                        contentScale = ContentScale.Crop,
                        error = painterResource(Res.drawable.download_icon),
                        placeholder = painterResource(Res.drawable.image_placeholder_icon)
                    )
                }
            }
            Spacer(Modifier.height(8.dp))

            if (isExpanded) {
                Box(Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
                    Column(Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(Res.string.amount),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = order.getAmount(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Spacer(Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(Res.string.delivery_cost),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = order.shippingType.getPrice(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Spacer(Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(Res.string.delivery_type),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = order.shippingType.title,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Spacer(Modifier.height(8.dp))

                        Column(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = stringResource(Res.string.address),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = order.address.getDeliveryAddress(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}






