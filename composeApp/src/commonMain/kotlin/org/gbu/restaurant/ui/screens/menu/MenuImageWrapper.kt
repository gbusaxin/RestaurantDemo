package org.gbu.restaurant.ui.screens.menu

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun MenuImageWrapper(
    modifier: Modifier = Modifier,
    alignment: Alignment,
    child: @Composable () -> Unit
) {
    val animationDuration = 700
    val scale = remember { Animatable(0.3f) }
    val rotation = remember { Animatable(20f) }
    val offset = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = 0.6f,
                stiffness = 200f
            )
        )
    }

    LaunchedEffect(Unit) {
        rotation.animateTo(0f, animationSpec = tween(durationMillis = animationDuration))
    }

    LaunchedEffect(Unit) {
        offset.animateTo(
            targetValue = 60f,
            animationSpec = tween(
                durationMillis = animationDuration / 2,
                easing = FastOutSlowInEasing
            )
        )
        offset.animateTo(
            targetValue = 0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = 200f
            )
        )
    }

    Box(
        modifier = modifier
            .offset(x = offset.value.dp)
            .graphicsLayer {
                this.rotationZ = rotation.value
            },
        contentAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .scale(scale.value)
                .rotate(rotation.value)
        ) {
            child()
        }
    }

}