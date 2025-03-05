package org.gbu.restaurant.data.entity

import androidx.compose.ui.graphics.painter.Painter

data class User(
    val id: String,
    val username: String,
    val fName: String,
    val lName: String,
    val phone: String? = null,
    val profile: Painter,
    val token: String? = null
)