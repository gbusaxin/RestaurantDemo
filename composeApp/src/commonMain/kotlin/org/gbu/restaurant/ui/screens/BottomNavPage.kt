package org.gbu.restaurant.ui.screens

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
import org.gbu.restaurant.business.common.Context
import org.gbu.restaurant.decompose.bottomnavholder.BottomNavComponent
import org.gbu.restaurant.ui.composables.BottomNavUI
import org.gbu.restaurant.ui.screens.cart.CartNav
import org.gbu.restaurant.ui.screens.home.HomeNav
import org.gbu.restaurant.ui.screens.profile.ProfileNav
import org.gbu.restaurant.ui.screens.wish_list.WishListNav

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun BottomNavPage(
    bottomNavComponent: BottomNavComponent,
    context: Context
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
                is BottomNavComponent.BottomNavChild.Cart -> {
                    CartNav(page.component, context = context)
                }

                is BottomNavComponent.BottomNavChild.Home -> {
                    HomeNav(page.component)
                }

                is BottomNavComponent.BottomNavChild.WishList -> {
                    WishListNav(page.component)
                }

                is BottomNavComponent.BottomNavChild.Profile -> {
                    ProfileNav(page.component)
                }

                else -> {
                    println("Unknown bottom nav page")
                }
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