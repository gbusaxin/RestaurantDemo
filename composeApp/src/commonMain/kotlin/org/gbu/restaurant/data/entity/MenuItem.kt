package org.gbu.restaurant.data.entity

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class MenuItem(
    val id: Long,
    val name: String,
    val shortDesc: String,
    val imageLink: DrawableResource,
    val ingredients: List<Ingredient>,
    val bgColor: Color
)