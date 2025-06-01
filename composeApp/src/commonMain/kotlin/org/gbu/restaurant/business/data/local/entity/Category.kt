package org.gbu.restaurant.business.data.local.entity

data class Category(
    val icon: String = "",
    val id: Long = 0L,
    val name: String = "",
    val parent: Int = 0
)
val category_all = Category(
    icon = "",
    id = -1L,
    name = "All",
    parent = 0
)