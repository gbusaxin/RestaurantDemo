package org.gbu.restaurant.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import org.gbu.restaurant.decompose.bottomnavholder.BottomNavComponent
import org.gbu.restaurant.decompose.root.RestaurantRootImpl
import org.gbu.restaurant.ui.composables.BottomNavUI
import org.gbu.restaurant.ui.screens.menu.MenuPage

@OptIn(ExperimentalDecomposeApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun BottomNavPage(
    bottomNavComponent: BottomNavComponent,
    onNavigationToMainChild: (child: RestaurantRootImpl.MainNavigationConfig) -> Unit,
    sharedTransitionScope: SharedTransitionScope
) {
    val configs = bottomNavComponent.configs
    val pages by bottomNavComponent.pages.subscribeAsState()
    val currentPageIndex = pages.selectedIndex
    val pagerState = rememberPagerState(0, pageCount = { configs.size })

    LaunchedEffect(currentPageIndex) {
        pagerState.scrollToPage(currentPageIndex)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colorScheme.background),
            state = pagerState,
            userScrollEnabled = false
        ) {
            when (val page = pages.items[it].instance) {
                is BottomNavComponent.BottomNavChild.Menu -> {
                    MenuPage(
                        menuViewModel = page.component.menuViewModel,
                        onClick = { menuItem ->
                            onNavigationToMainChild(
                                RestaurantRootImpl.MainNavigationConfig.MenuDetail(
                                    menuItem.id
                                )
                            )
                        },
                        sharedTransitionScope = sharedTransitionScope
                    )
                }

                else -> println("BottomNavPage else block")// TODO()
            }

        }
        BottomNavUI(
            pages = configs,
            current = configs[currentPageIndex]
        ) {
            bottomNavComponent.onNewPageSelected(index = it)
        }
    }
}