package org.gbu.restaurant.ui.screens.detail

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.DarkOrange
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.local.entity.Product
import org.gbu.restaurant.ui.composables.CircleButton
import org.gbu.restaurant.ui.composables.DefaultButton
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.ExpandingText
import org.gbu.restaurant.ui.composables.noRippleClickable
import org.gbu.restaurant.ui.composables.rememberCustomImagePainter
import org.gbu.restaurant.ui.screens.detail.viewmodel.DetailEvent
import org.gbu.restaurant.ui.screens.detail.viewmodel.DetailState
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.add_to_cart
import restaurantdemo.composeapp.generated.resources.product_details
import restaurantdemo.composeapp.generated.resources.total_cost

@Composable
fun DetailPage(
    state: DetailState,
    events: (DetailEvent) -> Unit,
    errors: Flow<UIComponent>,
    popUp: () -> Unit
) {
    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(DetailEvent.OnRetryNetwork) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier.fillMaxSize().align(Alignment.TopCenter)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 75.dp)
            ) {
                Box(Modifier.fillMaxWidth().height(400.dp)) {
                    Image(
                        painter = rememberCustomImagePainter(state.selectedImage),
                        contentDescription = "product selected image",
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(Modifier.padding(16.dp).align(Alignment.TopStart)) {
                        CircleButton(
                            modifier = Modifier.padding(4.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            onClick = { popUp() }
                        )
                    }

                    Box(Modifier.padding(16.dp).align(Alignment.BottomCenter).fillMaxWidth()) {
                        Card(
                            colors = CardDefaults.cardColors(),
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            shape = MaterialTheme.shapes.small
                        ) {
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(8.dp)
                            ) {
                                items(state.product.gallery) {
                                    ImageSliderBox(it) {
                                        events(DetailEvent.OnUpdateSelectedImage(it))
                                    }
                                }
                                if (state.product.gallery.isEmpty()) {
                                    items(2) {
                                        ImageSliderBox("") {}
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(32.dp))

                Column(Modifier.padding(vertical = 16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = state.product.category.name,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Filled.Star, "", tint = DarkOrange)
                            Text(
                                text = state.product.rate.toString(),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = state.product.title,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.headlineLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        stringResource(Res.string.product_details),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(Modifier.height(8.dp))

                    ExpandingText(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        text = state.product.description,
                        style = MaterialTheme.typography.bodySmall
                    ) {}

                    Spacer(Modifier.height(16.dp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        thickness = 1.dp,
                        color = Color.DarkGray
                    )

                    Spacer(Modifier.height(16.dp))
                }
            }

            Box(Modifier.align(Alignment.BottomCenter).fillMaxWidth()) {
                BuyButtonBox(state.product) {
                    events(DetailEvent.AddToBasket(state.product.id))
                }
            }
        }
    }
}

@Composable
fun BuyButtonBox(product: Product, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(.3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.total_cost),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = product.getPrice(),
                    style = MaterialTheme.typography.titleLarge
                )

                DefaultButton(
                    modifier = Modifier.fillMaxWidth(.7f).height(50.dp),
                    text = stringResource(Res.string.add_to_cart)
                ) { onClick() }
            }
        }
    }
}

@Composable
fun ImageSliderBox(it: String, onClick: () -> Unit) {
    Box(Modifier.size(65.dp).clip(MaterialTheme.shapes.small).padding(4.dp).noRippleClickable {
        onClick()
    }) {
        Image(
            painter = rememberCustomImagePainter(it),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}