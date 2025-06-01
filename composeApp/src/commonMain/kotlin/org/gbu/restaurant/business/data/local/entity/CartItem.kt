package org.gbu.restaurant.business.data.local.entity

import org.jetbrains.compose.resources.DrawableResource
import restaurantdemo.composeapp.generated.resources.Res

data class CartItem(
    val id: Long,
    val menuItemId: Long,
    val title: String,
    val description: String,
    val image: DrawableResource,
    val price: String,
    val count: Int
) {
    companion object {
        fun fakeList(): List<CartItem> =
            listOf(

            )
    }
}