package org.gbu.restaurant.ui.screens.menudetail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.DarkOrange
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.entity.MenuItem
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.screens.menudetail.viewmodel.MenuDetailEvent
import org.gbu.restaurant.ui.screens.menudetail.viewmodel.MenuDetailState
import org.gbu.restaurant.ui.screens.menudetail.viewmodel.MenuDetailsViewModel
import org.gbu.restaurant.ui.sensor.Listener
import org.gbu.restaurant.ui.sensor.SensorData
import org.gbu.restaurant.ui.sensor.SensorManager
import org.jetbrains.compose.resources.painterResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.cart_icon
import kotlin.math.PI

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MenuDetailsPage(
    errors: Flow<UIComponent>,
    state: MenuDetailState,
    events: (MenuDetailEvent) -> Unit,
    goBack: () -> Unit,
    onAddToCart: (MenuItem) -> Unit,
    sensorManager: SensorManager?,
    sharedTransitionScope: SharedTransitionScope
) {

    val imageRotation = remember { mutableStateOf(0) }
    val sensorDataLive = remember { mutableStateOf(SensorData(0.0f, 0.0f)) }
    val roll by derivedStateOf { (sensorDataLive.value.roll).coerceIn(-3f, 3f) }
    val pitch by derivedStateOf { (sensorDataLive.value.pitch).coerceIn(-2f, 2f) }

    val tweenDuration = 300

    sensorManager?.registerListener(object : Listener {
        override fun onUpdate(sensorData: SensorData) {
            sensorDataLive.value = sensorData
        }
    })

    val backgroundShadowOffset = animateIntOffsetAsState(
        targetValue = IntOffset((roll * 6f).toInt(), (pitch * 6f).toInt()),
        animationSpec = tween(tweenDuration)
    )
    val backgroundImageOffset = animateIntOffsetAsState(
        targetValue = IntOffset(-roll.toInt(), pitch.toInt()),
        animationSpec = tween(tweenDuration)
    )

    val toolbarOffsetHeightPx = remember { mutableStateOf(340f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(0f, 340f)
                imageRotation.value += (available.y * 0.5).toInt()
                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                imageRotation.value += ((delta * PI / 180) * 10).toInt()
                return super.onPostScroll(consumed, available, source)
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                imageRotation.value += available.y.toInt()
                return super.onPreFling(available)
            }
        }
    }

    val candidateHeight = maxOf(toolbarOffsetHeightPx.value, 300f)
    val listState = rememberLazyListState()
    val (fraction, setFraction) = remember { mutableStateOf(1f) }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(MenuDetailEvent.OnRetryNetwork) }
    ) {
        with(sharedTransitionScope) {

            if (sharedTransitionScope.isTransitionActive.not()) {
                setFraction(0f)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Yellow)
            ) {
                AnimatedContent(targetState = state.menuItem) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .nestedScroll(nestedScrollConnection),
                        state = listState
                    ) {
                        stickyHeader {
                            Box(
                                modifier = Modifier
                                    .shadow(
                                        elevation = if (fraction < 0.5) {
                                            ((1 - fraction) * 16).dp
                                        } else 0.dp,
                                        clip = false,
                                        ambientColor = Color(0xffCE5A01).copy(if (fraction < 0.1) 1f - fraction else 0f),
                                        spotColor = Color(0xffCE5A01).copy(if (fraction < 0.1) 1f - fraction else 0f)
                                    )
                                    .alpha(if (fraction < 0.2) 1f - fraction else 0f)
                                    .fillMaxWidth()
                                    .background(
                                        color = state.menuItem.bgColor,
                                        shape = RoundedCornerShape(
                                            bottomEnd = 35.dp,
                                            bottomStart = 35.dp
                                        )
                                    )
                                    .clip(
                                        RoundedCornerShape(
                                            bottomEnd = 35.dp,
                                            bottomStart = 35.dp
                                        )
                                    )
                                    .height(candidateHeight.dp)
                                    .then(
                                        Modifier.sharedElement(
                                            rememberSharedContentState(
                                                key = "item-container-${state.menuItem.id}"
                                            ),
                                            this@AnimatedContent,
                                        )
                                    )
                            ) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    //bg image and shadow
                                    state.menuItem.bgImage?.let {
                                        Image(
                                            painter = painterResource(it),
                                            contentDescription = "background image shadow",
                                            contentScale = ContentScale.FillWidth,
                                            modifier = Modifier
                                                .offset {
                                                    backgroundShadowOffset.value
                                                }.graphicsLayer {
                                                    scaleX = 1.050f
                                                    scaleY = 1.050f
                                                }.blur(radius = 8.dp),
                                            colorFilter = ColorFilter.tint(DarkOrange.copy(alpha = 0.3f))
                                        )
                                        Image(
                                            painter = painterResource(it),
                                            contentDescription = "background image",
                                            contentScale = ContentScale.FillWidth,
                                            modifier = Modifier
                                                .background(
                                                    color = Color.Transparent,
                                                    shape = RoundedCornerShape(
                                                        bottomEnd = 35.dp,
                                                        bottomStart = 35.dp
                                                    )
                                                ).offset {
                                                    backgroundImageOffset.value
                                                }.graphicsLayer {
                                                    shadowElevation = 8f
                                                    scaleX = 1.050f
                                                    scaleY = 1.050f
                                                },
                                            alpha = 1 - fraction
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .align(Alignment.Center)
                                    ) {
                                        Box {
                                            Image(
                                                painter = painterResource(state.menuItem.imageLink),
                                                contentDescription = "food image",
                                                modifier = Modifier
                                                    .aspectRatio(1f)
                                                    .align(Alignment.Center)
                                                    .windowInsetsPadding(WindowInsets.systemBars)
                                                    .padding(16.dp)
                                                    .rotate(imageRotation.value.toFloat())
                                                    .background(
                                                        color = Color.Transparent,
                                                        shape = CircleShape
                                                    )
                                                    .sharedBounds(
                                                        rememberSharedContentState(key = "item-image-${state.menuItem.id}"),
                                                        animatedVisibilityScope = this@AnimatedContent,
                                                        enter = fadeIn(),
                                                        exit = fadeOut(),
                                                        resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                                                    )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Ingredients(
                            menuItem = state.menuItem,
                            sharedTransitionScope = sharedTransitionScope,
                            animatedContentScope = this@AnimatedContent
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.systemBars)
                        .size(50.dp)
                        .padding(6.dp)
                        .alpha(alpha = if (fraction <= 0) 1f else 0f)
                        .background(color = Color.Black, shape = RoundedCornerShape(50))
                        .shadow(elevation = 16.dp)
                        .padding(5.dp)
                        .clickable {
                            goBack()
                        }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "go back",
                        tint = Color.White,
                        modifier = Modifier.size(65.dp)
                    )
                }
                AddToCartButton(
                    menuItem = state.menuItem,
                    onAddToCart = onAddToCart
                )
            }
        }
    }
}

@Composable
private fun AddToCartButton(
    menuItem: MenuItem,
    onAddToCart: (MenuItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Row(
            modifier = Modifier
                .background(color = Color.White.copy(alpha = 0.7f), shape = CircleShape)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = menuItem.price,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )

            Surface(
                modifier = Modifier
                    .size(48.dp)
                    .shadow(8.dp, CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer, CircleShape),
                shape = CircleShape,
                onClick = { onAddToCart(menuItem) }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(Res.drawable.cart_icon),
                        contentDescription = "add to cart",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(3.dp)
                            .size(65.dp)
                    )
                }
            }
        }
    }
}








