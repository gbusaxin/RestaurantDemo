package org.gbu.restaurant.ui.screens.wish_list.viewmodel

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.ViewState
import org.gbu.restaurant.business.data.local.entity.Category
import org.gbu.restaurant.business.data.local.entity.WishList
import org.gbu.restaurant.business.data.local.entity.category_all
import org.gbu.restaurant.business.data.network.common.ProgressBarState

data class WishListState(
    val categoryId: Long? = null,
    val page: Int = 1,
    val hasNextPage: Boolean = true,
    val wishList: WishList = WishList(),
    val selectedCategory: Category = category_all,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good
) : ViewState
