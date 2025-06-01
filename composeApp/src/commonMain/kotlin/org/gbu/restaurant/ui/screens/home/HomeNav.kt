package org.gbu.restaurant.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import org.gbu.restaurant.decompose.bottomnavholder.home.HomeNavComponent
import org.gbu.restaurant.ui.screens.categories.CategoriesPage
import org.gbu.restaurant.ui.screens.detail.DetailPage
import org.gbu.restaurant.ui.screens.detail.viewmodel.DetailEvent
import org.gbu.restaurant.ui.screens.notifications.NotificationsPage
import org.gbu.restaurant.ui.screens.search.SearchPage
import org.gbu.restaurant.ui.screens.settings.SettingsPage

@Composable
fun HomeNav(root: HomeNavComponent) {
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
                is HomeNavComponent.HomeNavChild.Home -> {
                    val viewModel = child.component.viewModel
                    HomePage(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        navigateToDetail = { child.component::navigateToDetail },
                        navigateToNotifications = { child.component::navigateToNotifications },
                        navigateToSettings = { child.component::navigateToSettings },
                        navigateToCategories = { child.component::navigateToCategories },
                        navigateToSearch = { id, sort ->
                            child.component.navigateToSearch(
                                id,
                                sort
                            )
                        }
                    )
                }

                is HomeNavComponent.HomeNavChild.Search -> {
                    val viewModel = child.component.viewModel
                    SearchPage(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        navigateToDetailPage = { child.component::navigateToDetailPage },
                        popUp = { child.component::popUp }
                    )
                }

                is HomeNavComponent.HomeNavChild.Detail -> {
                    val viewModel = child.component.viewModel
                    LaunchedEffect(child.component.id) {
                        viewModel.onTriggerEvent(event = DetailEvent.GetProduct(id = child.component.id))
                    }
                    DetailPage(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        popUp = { child.component::popUp }
                    )
                }

                is HomeNavComponent.HomeNavChild.Categories -> {
                    val viewModel = child.component.viewModel
                    CategoriesPage(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        popUp = { child.component::popUp },
                        navigateToSearch = { child.component.navigateToSearch(it) }
                    )
                }

                is HomeNavComponent.HomeNavChild.Settings -> {
                    val viewModel = child.component.viewModel
                    SettingsPage(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        action = viewModel.action,
                        popUp = { child.component::popUp },
                        logout = { TODO("navigate to LoginPage") }
                    )
                }

                is HomeNavComponent.HomeNavChild.Notifications -> {
                    val viewModel = child.component.viewModel
                    NotificationsPage(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        popUp = { child.component::popUp }
                    )
                }
            }
        }
    }
}