package org.gbu.restaurant.ui.screens.menu_detail.viewmodel

import androidx.compose.ui.graphics.Color
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.local.entity.MenuItem
import org.gbu.restaurant.business.data.network.common.ProgressBarState
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.image_placeholder_icon

data class MenuDetailState(
    val menuItem: MenuItem = MenuItem(1,"","", Res.drawable.image_placeholder_icon, emptyList(),
        Color.Red, price = "1"),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
) : ViewState