package org.gbu.restaurant.ui.screens.wish_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import org.gbu.restaurant.decompose.bottomnavholder.wish_list.WishListNavComponent

@Composable
fun WishListNav(root: WishListNavComponent) {
    val stack by root.pages.subscribeAsState()

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Children(
            stack = stack,
            modifier = Modifier.weight(1f)
        ) { childCreated ->
            when (val child = childCreated.instance) {
                is WishListNavComponent.WishListNavChild.WishList -> {
                    val viewModel = child.component.viewModel
                    WishListPage(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        navigateToDetail = { child.component.navigateToDetail(id = it) }
                    )
                }
            }
        }
    }
}