package org.gbu.restaurant.ui.screens.home

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.constants.SortConstants
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.local.entity.Category
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.ProductBox
import org.gbu.restaurant.ui.composables.noRippleClickable
import org.gbu.restaurant.ui.composables.rememberCustomImagePainter
import org.gbu.restaurant.ui.screens.home.viewmodel.HomeEvent
import org.gbu.restaurant.ui.screens.home.viewmodel.HomeState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.category
import restaurantdemo.composeapp.generated.resources.flash_sale
import restaurantdemo.composeapp.generated.resources.location
import restaurantdemo.composeapp.generated.resources.location_icon
import restaurantdemo.composeapp.generated.resources.newest_products
import restaurantdemo.composeapp.generated.resources.notifications_icon
import restaurantdemo.composeapp.generated.resources.search
import restaurantdemo.composeapp.generated.resources.search_icon
import restaurantdemo.composeapp.generated.resources.see_all
import restaurantdemo.composeapp.generated.resources.settings_icon
import restaurantdemo.composeapp.generated.resources.special_for_you

@Composable
fun HomePage(
    state: HomeState,
    events: (HomeEvent) -> Unit = {},
    errors: Flow<UIComponent>,
    navigateToDetail: (Long) -> Unit = {},
    navigateToNotifications: () -> Unit = {},
    navigateToSettings: () -> Unit = {},
    navigateToCategories: () -> Unit = {},
    navigateToSearch: (Long?, Int?) -> Unit = { _, _ -> }
) {
    val pagerState = rememberPagerState { state.home.banners.size }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(HomeEvent.OnRetryNetwork) }
    ) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Column(Modifier.fillMaxWidth().padding(16.dp)) {
                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = stringResource(Res.string.location),
                            style = MaterialTheme.typography.labelSmall
                        )
                        Spacer(Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.location_icon),
                                contentDescription = "location icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = state.home.address.getLocation(),
                                style = MaterialTheme.typography.labelMedium
                            )
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowDown,
                                "close icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Box(
                        modifier = Modifier.background(
                            color = MaterialTheme.colorScheme.primary.copy(
                                .2f
                            ),
                            shape = CircleShape
                        ).size(45.dp).noRippleClickable { navigateToNotifications() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.notifications_icon),
                            contentDescription = "notifications icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Spacer(Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        Modifier.fillMaxWidth(.8f).height(45.dp).border(
                            1.dp,
                            Color.DarkGray, MaterialTheme.shapes.small
                        ).noRippleClickable {
                            navigateToSearch(null, null)
                        }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize().padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.search_icon),
                                contentDescription = "search icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = stringResource(Res.string.search),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth().height(45.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.small
                            ).noRippleClickable { navigateToSettings() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.settings_icon),
                            contentDescription = "settings",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
            Column(Modifier.fillMaxWidth().padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(Res.string.special_for_you),
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                HorizontalPager(
                    state = pagerState,
                    verticalAlignment = Alignment.CenterVertically
                ) { page ->
                    BannerImage(state.home.banners.getOrNull(page)?.banner ?: "")
                }

                Spacer(Modifier.height(8.dp))

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    DotsIndicator(
                        totalDots = state.home.banners.size,
                        selectedIndex = pagerState.currentPage,
                        dotSize = 8.dp
                    )
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(Res.string.category),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = stringResource(Res.string.see_all),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.noRippleClickable { navigateToCategories() }
                    )
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(state.home.categories, key = { it.id }) {
                        CategoryBox(category = it) {
                            navigateToSearch(it.id, null)
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            stringResource(Res.string.flash_sale),
                            style = MaterialTheme.typography.titleLarge
                        )
                        TimerBox(state)
                    }
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(state.home.flashSale.products, key = { it.id }) {
                        ProductBox(
                            product = it,
                            onLikeClick = { events(HomeEvent.Like(it.id)) }
                        ) {
                            navigateToDetail(it.id)
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(Res.string.newest_products),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = stringResource(Res.string.see_all),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.noRippleClickable {
                            navigateToSearch(
                                null,
                                SortConstants.NEWEST
                            )
                        }
                    )
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(state.home.newestProduct, key = { it.id }) {
                        ProductBox(product = it, onLikeClick = {
                            events(HomeEvent.Like(it.id))
                        }) {
                            navigateToDetail(it.id)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimerBox(state: HomeState) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                shape = MaterialTheme.shapes.small
            ).padding(4.dp)
        ) {
            Text(
                text = state.time.hour.toString(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Text(
            text = ":",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelMedium
        )
        Box(
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                shape = MaterialTheme.shapes.small
            ).padding(4.dp)
        ) {
            Text(
                text = state.time.minute.toString(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Text(
            text = ":",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelMedium
        )
        Box(
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                shape = MaterialTheme.shapes.small
            ).padding(4.dp)
        ) {
            Text(
                text = state.time.second.toString(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
private fun CategoryBox(category: Category, onCategoryClick: () -> Unit) {
    Box(Modifier.padding(horizontal = 8.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.width(75.dp).noRippleClickable { onCategoryClick() }
        ) {
            Box(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                    shape = CircleShape
                ).size(60.dp).padding(12.dp)
            ) {
                AsyncImage(
                    category.icon,
                    contentDescription = "category image",
                    modifier = Modifier.fillMaxSize().size(55.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = category.name,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun BannerImage(it: String) {
    Box(modifier = Modifier.padding(horizontal = 8.dp)) {
        Card(
            colors = CardDefaults.cardColors(),
            modifier = Modifier.height(160.dp).fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Image(
                painter = rememberCustomImagePainter(it),
                contentDescription = "banner image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = Color.DarkGray,
    unSelectedColor: Color = Color.LightGray,
    dotSize: Dp = 8.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (index in 0 until totalDots) {
            val color by remember(selectedIndex) { derivedStateOf { Animatable(unSelectedColor) } }
            val size by animateDpAsState(if (index == selectedIndex) 20.dp else dotSize)

            LaunchedEffect(selectedIndex) {
                color.animateTo(
                    if (index == selectedIndex) selectedColor else unSelectedColor,
                    animationSpec = tween(300)
                )
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Dot(size = size, color = color.value, shape = RoundedCornerShape(16.dp))
            }
        }
    }
}

@Composable
private fun Dot(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    size: Dp = 8.dp,
    color: Color
) {
    Box(
        modifier = modifier.padding(horizontal = 3.dp).height(8.dp).width(size).clip(shape)
            .background(color)
    )
}








