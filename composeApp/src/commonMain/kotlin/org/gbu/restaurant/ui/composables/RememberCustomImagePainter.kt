package org.gbu.restaurant.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import coil3.ImageLoader
import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.resources.painterResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.error_icon
import restaurantdemo.composeapp.generated.resources.image_placeholder_icon

@Composable
fun rememberCustomImagePainter(
    model: Any?,
    imageLoader: ImageLoader,
    contentScale: ContentScale = ContentScale.Fit,
) = rememberAsyncImagePainter(
    model = model,
    imageLoader = imageLoader,
    error = painterResource(Res.drawable.error_icon),
    placeholder = painterResource(Res.drawable.image_placeholder_icon),
    contentScale = contentScale
)

@Composable
fun rememberCustomImagePainter(
    model: Any?,
    contentScale: ContentScale = ContentScale.Fit
) = rememberAsyncImagePainter(
    model = model,
    error = painterResource(Res.drawable.error_icon),
    placeholder = painterResource(Res.drawable.image_placeholder_icon),
    contentScale = contentScale
)