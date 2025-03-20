package org.gbu.restaurant.business.data.entity

import org.jetbrains.compose.resources.DrawableResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources._01_lemon_cheesecake
import restaurantdemo.composeapp.generated.resources._02_chocolate_cake_1

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
                CartItem(
                    id = 1L,
                    menuItemId = 1L,
                    title = "Quatro Formagi",
                    description = "Fresh pizza with different types of cheese. Very delicious. Good choice!",
                    image = Res.drawable._01_lemon_cheesecake,
                    price = "12.50",
                    2
                ),
                CartItem(
                    id = 2L,
                    menuItemId = 2L,
                    title = "Pepperoni",
                    description = "Pizza with pepperoni mmm that smells good ualala bravo maestro!",
                    image = Res.drawable._02_chocolate_cake_1,
                    price = "15.99",
                    4
                )
            )
    }
}