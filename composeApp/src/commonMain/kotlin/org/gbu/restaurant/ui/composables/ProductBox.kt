package org.gbu.restaurant.ui.composables

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.gbu.restaurant.DarkOrange
import org.gbu.restaurant.business.data.local.entity.Product
import org.jetbrains.compose.resources.painterResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.error_icon
import restaurantdemo.composeapp.generated.resources.image_placeholder_icon

@Composable
fun ProductBox(
    modifier: Modifier = Modifier.width(100.dp),
    product: Product,
    onLikeClick: () -> Unit,
    onClick: () -> Unit
) {
    Box(modifier.height(260.dp).padding(8.dp).noRippleClickable { onClick() }) {
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxWidth().fillMaxHeight(.8f).clip(MaterialTheme.shapes.small)) {
                AsyncImage(
                    product.image,
                    contentDescription = "product image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(Res.drawable.error_icon),
                    placeholder = painterResource(Res.drawable.image_placeholder_icon)
                )
                Box(Modifier.padding(8.dp).align(Alignment.TopEnd)){
                    Box(
                        modifier = Modifier.noRippleClickable { onLikeClick() }
                            .background(color = Color.Cyan.copy(alpha = .3f))
                            .size(30.dp).padding(6.dp)
                    ){
                        Icon(
                            if (product.isLike) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "like button",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Star, "like", tint = DarkOrange)
                    Text(product.rate.toString(), style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(Modifier.height(4.dp))
            Text(
                product.getPrice(),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}








