package org.gbu.restaurant.ui.screens.menudetail.viewmodel

import androidx.compose.ui.graphics.Color
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.entity.MenuItem
import org.gbu.restaurant.business.data.network.common.ProgressBarState
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources._04_fluffy_cake

data class MenuDetailState(
    val menuItem: MenuItem = MenuItem(1,"","", Res.drawable._04_fluffy_cake, emptyList(),
        Color.Red, price = "1"),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
) : ViewState