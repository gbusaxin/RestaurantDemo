package org.gbu.restaurant.ui.screens.menu_detail

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay
import org.gbu.restaurant.business.data.local.entity.MenuItem

@Composable
fun AnimateInEffect(
    intervalStart: Float = 0f,
    content: @Composable () -> Unit,
    menuItem: MenuItem
) {
    val visibility = remember { Animatable(0f) }
    val offset = remember { Animatable(30f) }

    LaunchedEffect(menuItem){
        delay((intervalStart * 800).toLong())
        visibility.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )
    }
    LaunchedEffect(menuItem) {
        delay((intervalStart * 800).toLong())
        delay(intervalStart.toLong())
        offset.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )
    }
    Box(modifier = Modifier.graphicsLayer {
        this.translationY = offset.value
        this.alpha = visibility.value
    }) {
        content()
    }
}








