package org.gbu.restaurant.business.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitViewController
import org.gbu.restaurant.globalLatitude
import org.gbu.restaurant.globalLongitude
import org.gbu.restaurant.mapViewController

@Composable
actual fun MapComponent(
    context: Context,
    onLatitude: (Double) -> Unit,
    onLongitude: (Double) -> Unit
) {
    onLatitude(globalLatitude)
    onLongitude(globalLongitude)
    UIKitViewController(
        factory = mapViewController,
        modifier = Modifier.fillMaxSize()
    )
}