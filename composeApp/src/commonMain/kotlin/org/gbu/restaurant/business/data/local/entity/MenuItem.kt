package org.gbu.restaurant.business.data.local.entity

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.settings_icon

data class MenuItem(
    val id: Long,
    val name: String,
    val shortDesc: String,
    val imageLink: DrawableResource,
    val ingredients: List<Ingredient>,
    val bgColor: Color,
    val bgImage: DrawableResource? = Res.drawable.settings_icon,
    val price: String
) {
    companion object {
        fun fakeList(): List<MenuItem> {
            return listOf(

            )
        }
    }
}